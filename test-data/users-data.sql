DROP DATABASE IF EXISTS diploma;
CREATE DATABASE diploma;
USE diploma;

CREATE TABLE `diploma`.`users` (
	`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	`role` VARCHAR(255),
	`login` VARCHAR(255),
	`email` VARCHAR(255),
	`password` VARCHAR(255),
	`name` VARCHAR(255),
	`lastname` VARCHAR(255),
	`confirmed` BIT(1),
	`can_publish` BIT(1),
	`dtype` VARCHAR(31)
);

USE diploma;

INSERT INTO `diploma`.`users` (`id`, `role`, `login`, `email`, `password`, `name`, `lastname`, `confirmed`, `can_publish`, `dtype`) VALUES
	(1, 'DEFAULT', 'Dawn Gomez', 'zmontes@campbell-hale.net', '$pbkdf2-sha256$185000$$bpogXTaZNXD750O0VpLB.sP.rMdrNZqBPFETP7bL5mc', 'Ashlee', 'Kristin', 'True', 'False', 'USER'),
	(2, 'USER', 'Kevin Willis', 'kevin44@gmail.com', '$pbkdf2-sha256$185000$$AMdV7VPHtnu4dU/iqdADmzSrd1PBaAb7zOB7BnedHjU', 'Jody', 'Meghan', 'True', 'False', 'LECTURER'),
	(3, 'USER', 'Ana Jones', 'paulwilliamson@hotmail.com', '$pbkdf2-sha256$185000$$YIBC9WNBm.sL0kOnOZFoeqdVrrWEY1K3bviBxKvWAJg', 'Teresa', 'Jaclyn', 'True', 'False', 'USER'),
	(4, 'USER', 'Daniel Jones', 'john36@flynn-francis.org', '$pbkdf2-sha256$185000$$h/0kSWmKwLsNfLnDTBz4uFgH/bVi/TjxTVfar0lREN4', 'Bradley', 'Tammy', 'True', 'True', 'USER'),
	(5, 'DEFAULT', 'Cassandra Golden', 'dbrennan@yahoo.com', '$pbkdf2-sha256$185000$$qp0wUyQe/DqOPUR0U6TDeDaNsdxhu2sYSPVug63sTEk', 'Melanie', 'Nicholas', 'True', 'True', 'LECTURER'),
	(6, 'USER', 'Michael Williams', 'maysjames@benitez.com', '$pbkdf2-sha256$185000$$67tXwprFMAhv9OhuJtEHJhnlbOr5R7xOd2t8Zw2RieA', 'Victoria', 'Joseph', 'True', 'True', 'LECTURER'),
	(7, 'USER', 'Steven Burns', 'ericchang@yahoo.com', '$pbkdf2-sha256$185000$$2Ib.YfYCcYMVhSU8UoAGPfQR/dCzeFAbQzDUT.3n98c', 'Ronald', 'Blake', 'False', 'False', 'USER'),
	(8, 'USER', 'Jessica Hernandez', 'ofloyd@morris.biz', '$pbkdf2-sha256$185000$$xiHvwQidQc4szAUfU9yctWeDCkCqAL47R1p13IaJtv8', 'Carrie', 'Julie', 'False', 'False', 'USER'),
	(9, 'USER', 'John Lin', 'austin97@diaz-beltran.biz', '$pbkdf2-sha256$185000$$5r3w4LCY1HAyArdShPl7DxWxxFHP23YJbVnotW06eIQ', 'David', 'Lisa', 'True', 'True', 'USER'),
	(10, 'USER', 'Kevin Payne', 'marcusmarshall@yahoo.com', '$pbkdf2-sha256$185000$$TuzFU8zTXO0B7ULiE7czZoUE0T58KREjgcCvCWWTDUw', 'Victoria', 'Ross', 'False', 'False', 'USER'),
	(11, 'USER', 'James Simon', 'woodstamara@gmail.com', '$pbkdf2-sha256$185000$$f2dHLmfM9DNGA6B8zMWD4WYA7BD3XZzcKkYMfizvV9k', 'Katherine', 'John', 'True', 'True', 'USER'),
	(12, 'USER', 'Dakota Murphy', 'hicksbrett@yahoo.com', '$pbkdf2-sha256$185000$$KEzjSUKcvA9BzCOr7tNHm8LX7ReRYoHtFLhyxsyZk/k', 'Jonathan', 'Melissa', 'False', 'False', 'USER'),
	(13, 'USER', 'Wanda Whitaker MD', 'hailey60@hotmail.com', '$pbkdf2-sha256$185000$$YpMF7iQuavJeQdA9ZxQWmAJR7Uktrbi6PltjXdZgzyw', 'Calvin', 'Lawrence', 'True', 'True', 'LECTURER'),
	(14, 'DEFAULT', 'James Ortiz', 'stephenhart@hotmail.com', '$pbkdf2-sha256$185000$$cE9cA27Z4j/os8AtNhrPxwzKoY3RTsNiT6r8FQkbu3U', 'Michael', 'Zachary', 'True', 'True', 'LECTURER'),
	(15, 'DEFAULT', 'Kristen Grant', 'veronica85@jones.com', '$pbkdf2-sha256$185000$$9GxEW7egVw8Rgj2/jwPNrcJZFpBP9dzqKFzWJg.UyWI', 'Kevin', 'Steven', 'False', 'False', 'USER'),
	(16, 'USER', 'Richard Walls', 'gwright@martin.com', '$pbkdf2-sha256$185000$$wnf27AjXFeuihN6wR3C6hEZ7lcwDKOlH7Ef1W/3gtas', 'Kristine', 'Elizabeth', 'False', 'False', 'LECTURER'),
	(17, 'DEFAULT', 'Anthony Cook', 'markbailey@gay.com', '$pbkdf2-sha256$185000$$JVoezpRlvujOGveyGRSwg7b7.E9qEgWsNBS1HKskSI4', 'James', 'John', 'False', 'False', 'USER'),
	(18, 'DEFAULT', 'Carrie Jackson', 'christopherchambers@lewis.net', '$pbkdf2-sha256$185000$$oraaVGtOSXlRnNKtViBfEtH98wjbk3EjmG1ae7CEgFo', 'Elizabeth', 'Felicia', 'True', 'True', 'USER'),
	(19, 'DEFAULT', 'Courtney Glover', 'ijones@hotmail.com', '$pbkdf2-sha256$185000$$bpc4Pxg0mET2/TR3sDGmI4m5DnQRaiyflbLkHs5lODE', 'Dennis', 'Carolyn', 'True', 'True', 'LECTURER'),
	(20, 'USER', 'Jill Richards', 'abigail13@edwards.com', '$pbkdf2-sha256$185000$$63mHH1Mju9xp9Preur61JOuSLywpp9.25u3jTEbjYok', 'Kathryn', 'Lisa', 'False', 'False', 'USER'),
	(21, 'DEFAULT', 'Susan Mueller', 'monica81@yahoo.com', '$pbkdf2-sha256$185000$$DLYQ2Qqg4uWK3aTMr7/XtukUJmN663IM.nuPO./l834', 'Kayla', 'Brian', 'True', 'True', 'USER'),
	(22, 'USER', 'Stephen Gibson', 'parkskimberly@martin.com', '$pbkdf2-sha256$185000$$1wWHtxtTcOM2s1hrmwdtEnNQamQEnMv7OTiipZGsdH0', 'Sarah', 'Chris', 'True', 'False', 'USER'),
	(23, 'USER', 'Brian Jacobson', 'russelljerry@yahoo.com', '$pbkdf2-sha256$185000$$LrGGeVcFB1dUnXzDeNhe8SFhv9FGd5klG9Kjfy4slkw', 'Victor', 'Jeffrey', 'False', 'False', 'USER'),
	(24, 'DEFAULT', 'Anthony Becker', 'danielkim@yahoo.com', '$pbkdf2-sha256$185000$$mcATHW0Cxbn1cdBPezCkOwtVaKULII6Z7eSaJL2w/LI', 'Kelsey', 'Pamela', 'False', 'False', 'USER'),
	(25, 'DEFAULT', 'Tracey Mcguire', 'amy60@case.com', '$pbkdf2-sha256$185000$$eJjCnwRvI2BiVJfX1db/NWuY5oxrl8d.2mm.Xnp/OTs', 'Christian', 'Valerie', 'False', 'False', 'USER'),
	(26, 'USER', 'Sandra Clark', 'trevor31@hotmail.com', '$pbkdf2-sha256$185000$$2wlc1ZfaCHoM5ce61Reu6ytv2v2XqXZMiqS.TuQagAU', 'Derrick', 'Devin', 'True', 'True', 'LECTURER'),
	(27, 'USER', 'Michael Goodman', 'douglasbentley@brown.net', '$pbkdf2-sha256$185000$$h12e/t6k15Lvv5BmmOmOLFoM8PTzm..hs4jJLB9r3Fo', 'Jacqueline', 'Kimberly', 'False', 'False', 'USER'),
	(28, 'USER', 'William Chavez', 'simpsonsarah@yahoo.com', '$pbkdf2-sha256$185000$$EEKe0OTMtola2kLFHiVZa2lcz0N3Jzz/MBWznaMxJuM', 'Richard', 'Robyn', 'False', 'False', 'USER'),
	(29, 'USER', 'Samantha Thomas', 'cbryant@yahoo.com', '$pbkdf2-sha256$185000$$7VO2HeYOOzKw7TU6noKZHfmv90nCUROG6VIhrCvYnzM', 'Kyle', 'Anna', 'False', 'False', 'USER'),
	(30, 'USER', 'Philip Lewis', 'gdavis@yahoo.com', '$pbkdf2-sha256$185000$$pvUDNQ6iKTxoCEk9m0txm4n2iD18/bGW0fY0xSP7mII', 'Caleb', 'Teresa', 'False', 'False', 'USER'),
	(31, 'USER', 'Rebecca Long', 'emily04@collins-young.com', '$pbkdf2-sha256$185000$$XuTS2VWaJcShus8sWITAEMwvLLeCcxEr6M2JaxHNCuw', 'Theresa', 'David', 'False', 'False', 'USER'),
	(32, 'USER', 'Maria Martinez', 'meyermichael@thompson-pierce.org', '$pbkdf2-sha256$185000$$L3bmVMvoOU2/.i4cL3jWjy5LJg1Orq6sORgK8FJ0a7E', 'Lori', 'Janice', 'False', 'False', 'USER'),
	(33, 'DEFAULT', 'Brianna Duncan', 'torresanna@harris-calderon.com', '$pbkdf2-sha256$185000$$eWY5bIPiT6c/oOh/PgSmC9sRNfN/f0NZTrxKrGt7sjE', 'Emily', 'Amy', 'True', 'True', 'USER'),
	(34, 'USER', 'Mark Wilson', 'hannahrose@bridges-smith.com', '$pbkdf2-sha256$185000$$R0O8CKqk/Qh4xywdk5kJ7qllLOO2kWUumgH5SHiriMA', 'Christine', 'Karen', 'True', 'True', 'USER'),
	(35, 'DEFAULT', 'Jennifer Vasquez', 'marshwilliam@lopez-mcguire.info', '$pbkdf2-sha256$185000$$pI/tYsah4ltw.BBJQ8F5Q3RwjEUuerXOgvUZq6brU5k', 'Grace', 'Robert', 'True', 'False', 'LECTURER'),
	(36, 'USER', 'William Davis', 'angelajames@yahoo.com', '$pbkdf2-sha256$185000$$PMHhKIWcanWVwaPwC683tMofvWDX1kOlua1udX.UhwU', 'Sandra', 'Jim', 'True', 'False', 'USER'),
	(37, 'DEFAULT', 'Selena Gardner', 'christopher35@yahoo.com', '$pbkdf2-sha256$185000$$oNOy/apUT/U8bcSNxJKTu9.ROHSsJPnLV5nOJ8Oc2oM', 'Elaine', 'Jose', 'True', 'False', 'USER'),
	(38, 'USER', 'Carly Davis', 'ronald38@dixon.org', '$pbkdf2-sha256$185000$$VzDpKPL9Z/D.tNSkDxanG0L2S4s1fI.ZD1ZvD8Z219Y', 'Sarah', 'Michael', 'False', 'False', 'USER'),
	(39, 'USER', 'Steven Green', 'laura70@jimenez-riggs.biz', '$pbkdf2-sha256$185000$$d125aTB6.mQLsxzXryxP7v3Vtgdu4p.Oy4jJex7qxeU', 'Madison', 'Caleb', 'True', 'False', 'LECTURER'),
	(40, 'DEFAULT', 'Amy Parker', 'castromichael@trevino.com', '$pbkdf2-sha256$185000$$xHzfUOyaKjC8g2bu45/tnhcoKl.YA/SIaGvlVuoHK6M', 'Thomas', 'Linda', 'False', 'False', 'USER'),
	(41, 'USER', 'Danny Campbell', 'monica27@hotmail.com', '$pbkdf2-sha256$185000$$r1l.JG6ZYTwZiN5O2BT15opL0FHBGFVqJWKcvdMqepo', 'Benjamin', 'Mary', 'True', 'True', 'LECTURER'),
	(42, 'USER', 'Kenneth Walters', 'dporter@yahoo.com', '$pbkdf2-sha256$185000$$vu.iEisME5XugeVqcjxtLWwlKr03pyzT1YYLoaepTws', 'Jose', 'Alvin', 'False', 'False', 'USER'),
	(43, 'USER', 'Melissa Grant', 'thomascindy@gmail.com', '$pbkdf2-sha256$185000$$ASQvD6RxeTA.QzUbrenWFuSAg3YApYTHOhP20CayC4M', 'Courtney', 'John', 'False', 'False', 'USER'),
	(44, 'USER', 'Jacqueline Hatfield', 'lee71@hotmail.com', '$pbkdf2-sha256$185000$$F6xsoX7Y7gEykFwefPrBhqHebjpE6giS/OQdijzVXRs', 'Patricia', 'Karen', 'False', 'False', 'USER'),
	(45, 'USER', 'Michael Davis', 'derek19@gmail.com', '$pbkdf2-sha256$185000$$0vAHJNJS45oafGvA45654ue.SYOL7rYm1jBEWfmsq5s', 'Timothy', 'Lauren', 'False', 'False', 'USER'),
	(46, 'DEFAULT', 'Brady Knox', 'thomas27@hotmail.com', '$pbkdf2-sha256$185000$$DQ5g4zC83BYb/bQYmT.n9BibM9cYmC.UrQjE3NFztbc', 'Jennifer', 'William', 'False', 'False', 'USER'),
	(47, 'USER', 'Jeremy Spencer', 'zmorgan@yahoo.com', '$pbkdf2-sha256$185000$$hiUcfhBflCLfdITVrBoa5j2E/MQ/vb8d.e0wRZsiD8M', 'Derek', 'Lauren', 'False', 'False', 'USER'),
	(48, 'DEFAULT', 'Dustin Kim', 'pwheeler@yahoo.com', '$pbkdf2-sha256$185000$$RqP1K.Dc7BggEuOZuCg7ZyEQmumDGxNgPcZW2cH62Ro', 'Vanessa', 'Daniel', 'True', 'True', 'LECTURER'),
	(49, 'DEFAULT', 'Sarah Johnson', 'barnesjohn@gmail.com', '$pbkdf2-sha256$185000$$m25P.R2I//yxgDUh31EmltK.0noGmVVAIB7vxu2mzT0', 'Mary', 'Vanessa', 'False', 'False', 'LECTURER'),
	(50, 'USER', 'Kelly Benitez', 'williamevans@yahoo.com', '$pbkdf2-sha256$185000$$Mq/mpUFVgAMbJ53w47NiBm29PLQWntUxAbQSWFie0Ng', 'Troy', 'Isaac', 'True', 'True', 'USER'),
	(51, 'DEFAULT', 'Mark Rodriguez', 'carneyerin@turner-reynolds.com', '$pbkdf2-sha256$185000$$Rp/eLgwpvrrKAzx9ydlBYtaN9Pk0zVWZD6Dpzx.v2aU', 'James', 'Samantha', 'True', 'True', 'USER'),
	(52, 'USER', 'Stephen Harris', 'diane31@gmail.com', '$pbkdf2-sha256$185000$$x2GuDbYJcMAIt9ZkSD6nMvRP7.dIbjCRN38OdpsGr1A', 'Benjamin', 'Pamela', 'False', 'False', 'USER'),
	(53, 'DEFAULT', 'Heather Lewis', 'kathryn01@warren.com', '$pbkdf2-sha256$185000$$1.nhRcEyd/h231.T.JgLZE9n/Kq1ylPNi.4EEJ3R7.s', 'Victoria', 'Krista', 'False', 'False', 'LECTURER'),
	(54, 'USER', 'Alyssa Reyes', 'briannarivera@bauer.com', '$pbkdf2-sha256$185000$$V9JPKLDNiup/rsaxQcF3nxdD6KzjouHPmcWAuV/PyU4', 'Rachel', 'Terry', 'False', 'False', 'USER'),
	(55, 'USER', 'Nicole Patterson', 'jaime25@jacobs.com', '$pbkdf2-sha256$185000$$GNiTT/ODE5L99Yc7ZPLeSkGD4SBYsVZDezgf4ZA.0oY', 'Alison', 'Dylan', 'True', 'True', 'USER'),
	(56, 'DEFAULT', 'Alexandra Evans', 'pscott@hotmail.com', '$pbkdf2-sha256$185000$$VtngHY0uDmmfsN8N7QYqK8dB0UwUn5i/daR1lNr42Sg', 'Kari', 'Joshua', 'False', 'False', 'USER'),
	(57, 'USER', 'Joshua Fowler', 'angeladonaldson@mcdaniel.com', '$pbkdf2-sha256$185000$$wbrH3IABPvyvoq2EORcaPyyLpDLzwOHgLRjG3wZsBAQ', 'William', 'Joshua', 'False', 'False', 'LECTURER'),
	(58, 'DEFAULT', 'Victoria Wright', 'kimwarner@yahoo.com', '$pbkdf2-sha256$185000$$A6i5E6pF6Ly004wQ6/.em.DnDMq4tkOThyAxVeVAERQ', 'Annette', 'Gabriela', 'False', 'False', 'LECTURER'),
	(59, 'DEFAULT', 'Tracy Glenn', 'adamsjason@ayers.net', '$pbkdf2-sha256$185000$$d2U.9GmaZAA3gDcKxTp9Y2KVXy7N7Lznm/XbT9fRYT8', 'John', 'April', 'False', 'False', 'LECTURER'),
	(60, 'DEFAULT', 'Anthony Glover', 'ckaiser@gmail.com', '$pbkdf2-sha256$185000$$x1RlGywxY5rkImmgqJIhwFU3jl.QRQSuSrFsu4ZP8lg', 'Adriana', 'Mary', 'False', 'False', 'LECTURER'),
	(61, 'USER', 'Jennifer Russell', 'patriciasimmons@rodriguez.com', '$pbkdf2-sha256$185000$$Mf9TxbTwikPvyYA3XuQ4SAKQtZHqAXKJzj7Y9sYsIDg', 'Tamara', 'Barbara', 'False', 'False', 'USER'),
	(62, 'USER', 'Lawrence Santana', 'qyoder@jones.net', '$pbkdf2-sha256$185000$$uOul0U8Ugf6SqyBIdgVLTyUkjhiTPoauBugtR20aUjs', 'Jonathan', 'Melissa', 'True', 'True', 'USER'),
	(63, 'USER', 'Lisa Webb', 'rachelflynn@higgins-bryan.com', '$pbkdf2-sha256$185000$$EyV74Uwzswujo9CuxydgEVVFzUP9IknPZV9wlPNhZZQ', 'Jane', 'Dylan', 'False', 'False', 'USER'),
	(64, 'DEFAULT', 'Marie Smith', 'amyharris@gmail.com', '$pbkdf2-sha256$185000$$52kd5g6SBSPTgtQxNPVHMnHYYxufCrqUI2CoHZcHvr4', 'Marco', 'Chloe', 'False', 'False', 'LECTURER'),
	(65, 'DEFAULT', 'Holly Gill', 'lgreen@gmail.com', '$pbkdf2-sha256$185000$$MZrFsL0hq6ISqd6zv2di4AdoQwO.inppkMYboN8aWaA', 'Brandon', 'Katelyn', 'False', 'False', 'USER'),
	(66, 'DEFAULT', 'Barbara Richardson', 'ericagarza@foster.com', '$pbkdf2-sha256$185000$$sdUK55sb6pDZmztJyNktxewyoVmHWJG0Prg9EA3ifoo', 'Jonathon', 'Christine', 'True', 'True', 'USER'),
	(67, 'DEFAULT', 'Ryan Olson', 'lindsaythompson@yahoo.com', '$pbkdf2-sha256$185000$$xPHCj111dAP2iWAJgOTkxoJ2gDdvRKeDnqSElzbW29I', 'Tyler', 'Lauren', 'True', 'True', 'USER'),
	(68, 'USER', 'Cynthia Estrada', 'sherri76@hotmail.com', '$pbkdf2-sha256$185000$$CPo9uOLrDSIysI0PFeRjSvwV4gb5jYA7KUd9Hs7OQwk', 'Regina', 'Susan', 'False', 'False', 'USER'),
	(69, 'DEFAULT', 'Paul Smith', 'falvarez@rose-johnson.com', '$pbkdf2-sha256$185000$$xflfsxJOIgT0oSrB33cWC/QvXWbbshFVxlJ1jnFXAmw', 'Alyssa', 'Lindsay', 'True', 'False', 'USER'),
	(70, 'USER', 'Diana Huynh', 'phoffman@ashley.com', '$pbkdf2-sha256$185000$$/2LEl.1wlFXaz4tXL0ZFYKwyBtMQcGkeVo7ekm4nLNs', 'Yolanda', 'Amy', 'True', 'False', 'LECTURER'),
	(71, 'DEFAULT', 'Cynthia Wright', 'jennifer05@yahoo.com', '$pbkdf2-sha256$185000$$Xf5YSBB94iIDg3oQTdLVj7/9j8L3kbhUYCrl0Ca/t9g', 'Patricia', 'Mark', 'False', 'False', 'USER'),
	(72, 'DEFAULT', 'Ricky Ochoa', 'summerhernandez@gmail.com', '$pbkdf2-sha256$185000$$Uvw.ynyjFjAEHPD9h5802yyKcqMDgRctvIOFdLQiBZk', 'Kara', 'Jo', 'False', 'False', 'USER'),
	(73, 'USER', 'Edward Massey', 'roberterickson@austin-nichols.org', '$pbkdf2-sha256$185000$$AxmLoA0ThB2msqGc1.7rGzYGq1dqGVCVaJpNGUgtKPA', 'Ashley', 'Andrew', 'True', 'False', 'LECTURER'),
	(74, 'USER', 'Richard Moore', 'paul45@hernandez-oconnell.com', '$pbkdf2-sha256$185000$$SvTD5hxNv7oCvttSwvq1vy0tvKMP3yyDmxsbk4Q0nZI', 'Carolyn', 'Tiffany', 'True', 'True', 'LECTURER'),
	(75, 'DEFAULT', 'Jennifer Cline', 'david10@gmail.com', '$pbkdf2-sha256$185000$$fsYDPi9Gs/.0kJnTTG/Y2YKWgNN8TR20tDE5Vtkw16k', 'Patricia', 'Wayne', 'False', 'False', 'USER'),
	(76, 'DEFAULT', 'Mark Jacobs', 'ricardoayala@yahoo.com', '$pbkdf2-sha256$185000$$9RnipnXI4aCjswC7bTiOjkYafHc0ShCpdYgRx5c6/AY', 'Alexandra', 'Valerie', 'True', 'False', 'USER'),
	(77, 'DEFAULT', 'Brittany Cross', 'valeriesimpson@davis-hart.net', '$pbkdf2-sha256$185000$$xPreUQfu9RAkSFRSrJkZKQerKDdxjPJeggA1pd9jt1o', 'Cynthia', 'Caitlin', 'False', 'False', 'LECTURER'),
	(78, 'DEFAULT', 'Lisa Davis', 'vmendoza@price.info', '$pbkdf2-sha256$185000$$R2GLaSKB6ayYfA6cZdgLOzzF2eWDhWKeWSoKnOuOre0', 'Stephanie', 'Edgar', 'False', 'False', 'USER'),
	(79, 'USER', 'Maxwell Newman', 'ashley74@gmail.com', '$pbkdf2-sha256$185000$$iFQhFy3CF7//8Iyvfb4LNwAUZjWlXU8lPiaVtpSALcM', 'Debbie', 'Carrie', 'False', 'False', 'USER'),
	(80, 'DEFAULT', 'Krystal Jackson', 'ewarner@kennedy.com', '$pbkdf2-sha256$185000$$tOaRHy9teAkW1GoXquFsvnGa2aXoml/kiYxpWoEHMGg', 'Shelley', 'Anna', 'False', 'False', 'LECTURER'),
	(81, 'USER', 'Katherine Mejia', 'fletcherderek@hotmail.com', '$pbkdf2-sha256$185000$$cmDPMYNWRvKdUjxxYP2SUKDYgnsVPO5HPVu/7i4NqmA', 'Gregory', 'Rachel', 'True', 'True', 'USER'),
	(82, 'USER', 'David Dennis', 'lebrandon@gates.com', '$pbkdf2-sha256$185000$$pswt7ZySN5KdBgC6iGRQE8AvR.L8XClu4Wyw0feiyLM', 'Angela', 'Lisa', 'False', 'False', 'USER'),
	(83, 'DEFAULT', 'Michael Williams', 'courtneymercado@wise.com', '$pbkdf2-sha256$185000$$m/AcRNUttKgTf5.d3sXKy21gFpQTX5vtdNM14.pmZu0', 'Jeremy', 'Rodney', 'True', 'True', 'USER'),
	(84, 'USER', 'Daniel Michael', 'thomasmartinez@hotmail.com', '$pbkdf2-sha256$185000$$aMV9Sm0lUBd58/wCmonUrIFB3lsXavyHHqmgDngBA2c', 'Elizabeth', 'Donna', 'True', 'False', 'LECTURER'),
	(85, 'USER', 'Robert Johnson', 'snyderrichard@clark-romero.info', '$pbkdf2-sha256$185000$$aY.dp3p6Pi8Lw3CUdlh1/NH4FCgrM8hemjhzjmSMH4A', 'Dana', 'Danny', 'False', 'False', 'LECTURER'),
	(86, 'DEFAULT', 'Katelyn Smith', 'davidgonzalez@yahoo.com', '$pbkdf2-sha256$185000$$E9W3RlGpNzPkfD4gFqaAYPMP8fYzNAYvJSxOv5YEXOg', 'Adam', 'Vanessa', 'False', 'False', 'USER'),
	(87, 'DEFAULT', 'Melissa Bowman', 'erosario@patterson.info', '$pbkdf2-sha256$185000$$3/rbphfk0HZSY0ZmJugcP41sGUIOYZFK8lp0wGUAqHk', 'Crystal', 'Brittany', 'False', 'False', 'LECTURER'),
	(88, 'DEFAULT', 'Keith Thomas', 'scottmelissa@hotmail.com', '$pbkdf2-sha256$185000$$Mxq95HQTJg2DkD2o.oOG7cZ1xMlXLtJvL.OX4xDmbko', 'Matthew', 'Christopher', 'False', 'False', 'USER'),
	(89, 'DEFAULT', 'Rachel Johnson', 'alejandra88@yahoo.com', '$pbkdf2-sha256$185000$$vqtTsZYMunEIKqv/ciGzqLIruEzRFijfuGpwcijMD2M', 'Melissa', 'Connie', 'True', 'True', 'LECTURER'),
	(90, 'DEFAULT', 'Allen Rodriguez', 'micheal56@clark.com', '$pbkdf2-sha256$185000$$M.m/LEL3o1wzV/ijeCYSJfosEcQb7uvY5G.yJPvGO2s', 'Julian', 'Joshua', 'True', 'True', 'USER'),
	(91, 'DEFAULT', 'Lisa Tran', 'taylorreginald@gmail.com', '$pbkdf2-sha256$185000$$WdmBvdkAXZjhCvyApKs/bgU7TbwOfo2mGBRB2n3hnhE', 'Emily', 'Nathan', 'False', 'False', 'USER'),
	(92, 'DEFAULT', 'Evan Fox', 'margaret00@yahoo.com', '$pbkdf2-sha256$185000$$DlII1eQ7mRvvLCGJ7zV14cSdrBaG/I2mZ8Orqex7sCw', 'Shawn', 'Anthony', 'True', 'False', 'USER'),
	(93, 'USER', 'Lisa Dixon', 'wmoore@dennis.org', '$pbkdf2-sha256$185000$$wrlyhlAg46SyMtcFGmPVD9qZo7FMaaAXMrXTb63BKVg', 'Dylan', 'Kristen', 'True', 'True', 'USER'),
	(94, 'DEFAULT', 'Dale Phelps', 'robert04@boyer.com', '$pbkdf2-sha256$185000$$zhMdvdXv8QQDU5cg7mM7TYtSlb28xqrKR39bioNuRUo', 'Steven', 'Christine', 'True', 'True', 'USER'),
	(95, 'USER', 'Hector Brewer', 'james48@anderson-bush.net', '$pbkdf2-sha256$185000$$8E93WMB1yzAsV/t3JVC5RzDiBtXa7GjlM0bSqrch2ZY', 'Daniel', 'Eddie', 'True', 'False', 'LECTURER'),
	(96, 'DEFAULT', 'Veronica Perry', 'meyersryan@hotmail.com', '$pbkdf2-sha256$185000$$hyGbKMG0wz7aTy4Lmudw4KU2BFwLlanJCEMhXnUGDbE', 'Jack', 'Kimberly', 'True', 'True', 'USER'),
	(97, 'DEFAULT', 'Jason Powell', 'tklein@duran.info', '$pbkdf2-sha256$185000$$9un7aLF0yEM6NOsO7tU6eMBJ3ExPhwMZzqVgAQg7L9c', 'Renee', 'Edward', 'False', 'False', 'LECTURER'),
	(98, 'USER', 'Lauren Munoz', 'santanadavid@brown-fisher.com', '$pbkdf2-sha256$185000$$/cVeI9Se08w1PPRNa8QnaozktetmD9Rmg5.EBR9VBL4', 'Kenneth', 'Benjamin', 'True', 'False', 'USER'),
	(99, 'DEFAULT', 'Mr. Anthony Beasley', 'dale47@gmail.com', '$pbkdf2-sha256$185000$$mX2jJ.VMiTowD2YfnihZtdRaJc7oJA2do.fXFWWdDQY', 'David', 'Lisa', 'False', 'False', 'LECTURER'),
	(100, 'DEFAULT', 'Marcus Bryant', 'mary27@wilcox.info', '$pbkdf2-sha256$185000$$kBw.OZP71wk8lKtpP6M07acJURDq7KTLePzbuq42EVE', 'Jason', 'Brian', 'True', 'False', 'LECTURER');

