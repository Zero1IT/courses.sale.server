import random
import binascii

from faker import Faker
from hashlib import pbkdf2_hmac
from os import urandom
from sqlfaker.database import Database


class MyFaker(Faker):
    def __init__(self, db, **config):
        super().__init__(**config)
        self.lecture_state = []
        self.can_publish_iteration = 0
        self.roles = ["DEFAULT", "USER", "LECTURER"]
        self.dtype_iter = 0
        self.db = db
        
    def boolean_bit(self):
        return 1 if self.boolean() else 0

    def diploma_roles(self):
        return random.choice(self.roles)

    def hibernate_dtype(self):
        dt = "USER"
        if self.db.tables["users"].columns["role_id"].data[self.dtype_iter] == "LECTURER":
            dt = "Lecturer"
        self.dtype_iter += 1
        return dt
        
    def can_publish_lecturer(self):
        can = False
        if self.db.tables["users"].columns["dtype"].data[self.can_publish_iteration] == "Lecturer":
            can = self.boolean()
        self.can_publish_iteration += 1
        return 1 if can else 0
        
    def owner_lecturer_key(self):
        if len(self.lecture_state) == 0:
            self.save_lecturer_state()
        
        return random.choice(self.lecture_state)
    
    def save_lecturer_state(self):
        filtered = enumerate(self.db.tables["users"].columns["dtype"].data)
        filtered = filter(
            lambda c: c[1] == "Lecturer" and self.db.tables["users"].columns["can_publish"].data[c[0]], filtered
        )
        self.lecture_state = list(map(
            lambda c: self.db.tables["users"].columns["id"].data[c[0]], filtered
        ))
        if len(self.lecture_state) == 0:
            raise Exception("Not found data for foreign keys lecturer with can_publish==True")
    
        
    def reset_faker_state_storage(self):
        self.confirmed_state.clear()
        self.lecture_state.clear()

db = Database(db_name="diploma")
db.lang = "en_EN"
db.add_table(table_name="users", n_rows=100)
db.add_table(table_name="courses", n_rows=200)


db.tables["users"].add_primary_key(column_name="id")
db.tables["users"].add_column(column_name="role_id", data_type="varchar(255)", data_target="diploma_roles")
db.tables["users"].add_column(column_name="login", data_type="varchar(255)", data_target="name")
db.tables["users"].add_column(column_name="email", data_type="varchar(255)", data_target="email")
db.tables["users"].add_column(column_name="password", data_type="varchar(255)", data_target="password")
db.tables["users"].add_column(column_name="name", data_type="varchar(255)", data_target="first_name")
db.tables["users"].add_column(column_name="lastname", data_type="varchar(255)", data_target="first_name")
db.tables["users"].add_column(column_name="confirmed", data_type="int", data_target="boolean_bit")
db.tables["users"].add_column(column_name="dtype", data_type="varchar(31)", data_target="hibernate_dtype")
db.tables["users"].add_column(column_name="can_publish", data_type="int", data_target="can_publish_lecturer")


db.tables["courses"].add_primary_key(column_name="id")
db.tables["courses"].add_column(column_name="description", data_type="text", data_target="text")
db.tables["courses"].add_column(column_name="title", data_type="varchar(255)", data_target="file_name")
db.tables["courses"].add_column(column_name="closed", data_type="int", data_target="boolean_bit")
db.tables["courses"].add_column(column_name="ended", data_type="int", data_target="boolean_bit")
db.tables["courses"].add_column(column_name="start_date", data_type="datetime(6)", data_target="date_time")
db.tables["courses"].add_column(column_name="deferred_payment_days", data_type="int", data_target="random_digit")
db.tables["courses"].add_column(column_name="owner_id", data_type="int", data_target="owner_lecturer_key")


db.generate_data(faker_t=MyFaker(db), ordered=True)
db.export_sql("generated-fake-data.sql")
