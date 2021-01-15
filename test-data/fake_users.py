import random
import binascii

from faker import Faker
from passlib.hash import pbkdf2_sha256
from hashlib import pbkdf2_hmac
from os import urandom
from sqlfaker.database import Database


class MyFaker(Faker):
    def __init__(self, **config):
        super().__init__(**config)
        #self.encoder = pbkdf2_sha256.using(salt_size=8, rounds=185000)
        self.confirmed_state = []
        self.can_publish_iteration = 0

    def custom_user_role(self):
        return "DEFAULT" if random.randint(0, 3) < 2 else "USER"

    def hibernate_dtype(self):
        return "USER" if random.randint(0, 3) < 3 else "LECTURER"

    def default_spring_pbkdf2(self):
        pasw = self.password()
        print(pasw)
        return pbkdf2_sha256.hash(pasw, rounds=185000, salt=b'')
        
    def is_confirmed(self):
        confirmed = self.boolean()
        self.confirmed_state.append(confirmed)
        return confirmed
        
    def can_publish_by_confirmed_state(self):
        result = self.boolean() if self.confirmed_state[self.can_publish_iteration] else False
        self.can_publish_iteration += 1
        return result
        
    def reset_faker_state_storage(self):
        self.confirmed_state.clear()

db = Database(db_name="diploma")
db.add_table(table_name="users", n_rows=100)
db.lang = "en_EN"

db.tables["users"].add_primary_key(column_name="id")
db.tables["users"].add_column(column_name="role", data_type="varchar(255)", data_target="custom_user_role")
db.tables["users"].add_column(column_name="login", data_type="varchar(255)", data_target="name")
db.tables["users"].add_column(column_name="email", data_type="varchar(255)", data_target="email")
db.tables["users"].add_column(column_name="password", data_type="varchar(255)", data_target="default_spring_pbkdf2")
db.tables["users"].add_column(column_name="name", data_type="varchar(255)", data_target="first_name")
db.tables["users"].add_column(column_name="lastname", data_type="varchar(255)", data_target="first_name")
db.tables["users"].add_column(column_name="confirmed", data_type="bit(1)", data_target="is_confirmed")
db.tables["users"].add_column(column_name="can_publish", data_type="bit(1)", data_target="can_publish_by_confirmed_state")
db.tables["users"].add_column(column_name="dtype", data_type="varchar(31)", data_target="hibernate_dtype")


db.generate_data(faker_t=MyFaker())
db.export_sql("users-data.sql")
