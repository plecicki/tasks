CREATE TABLE BOOKS_AUD (
                           EVENT_ID INT(11) NOT NULL AUTO_INCREMENT,
                           EVENT_DATE DATETIME NOT NULL,
                           EVENT_TYPE VARCHAR(10) DEFAULT NULL,
                           BOOK_ID INT(11) NOT NULL,
                           OLD_TITLE VARCHAR(255),
                           NEW_TITLE VARCHAR(255),
                           OLD_PUBYEAR INT(4),
                           NEW_PUBYEAR INT(4),
                           OLD_BESTSELLER BOOLEAN,
                           NEW_BESTSELLER BOOLEAN,
                           PRIMARY KEY (EVENT_ID)
);

DELIMITER $$

CREATE TRIGGER BOOKS_INSERT AFTER INSERT ON BOOKS                                        -- [1]
    FOR EACH ROW
BEGIN
    INSERT INTO BOOKS_AUD (EVENT_DATE, EVENT_TYPE, BOOK_ID, NEW_TITLE, NEW_PUBYEAR, NEW_BESTSELLER)                                -- [3]
        VALUE(CURTIME(), "INSERT", NEW.BOOK_ID, NEW.TITLE, NEW.PUBYEAR, NEW.BESTSELLER);                                                           -- [5]
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER BOOKS_DELETE AFTER DELETE ON BOOKS                                        -- [1]
    FOR EACH ROW
BEGIN
    INSERT INTO BOOKS_AUD (EVENT_DATE, EVENT_TYPE, BOOK_ID, OLD_TITLE, OLD_PUBYEAR, OLD_BESTSELLER)                                -- [3]
        VALUE(CURTIME(), "DELETE", OLD.BOOK_ID, OLD.TITLE, OLD.PUBYEAR, OLD.BESTSELLER);                                                           -- [5]
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER BOOKS_UPDATE AFTER UPDATE ON BOOKS                                        -- [1]
    FOR EACH ROW
BEGIN
    INSERT INTO BOOKS_AUD (EVENT_DATE, EVENT_TYPE, BOOK_ID, OLD_TITLE, NEW_TITLE, OLD_PUBYEAR, NEW_PUBYEAR, OLD_BESTSELLER, NEW_BESTSELLER)                              -- [3]
        VALUE(CURTIME(), "UPDATE", OLD.BOOK_ID, OLD.TITLE, NEW.TITLE, OLD.PUBYEAR, NEW.PUBYEAR, OLD.BESTSELLER, NEW.BESTSELLER);                                                           -- [5]
END $$

DELIMITER ;

CREATE TABLE READERS_AUD (
                             EVENT_ID INT(11) NOT NULL AUTO_INCREMENT,
                             EVENT_DATE DATETIME NOT NULL,
                             EVENT_TYPE VARCHAR(10) DEFAULT NULL,
                             READER_ID INT(11) NOT NULL,
                             OLD_FIRSTNAME VARCHAR(255),
                             NEW_FIRSTNAME VARCHAR(255),
                             OLD_LASTNAME VARCHAR(255),
                             NEW_LASTNAME VARCHAR(255),
                             OLD_PESELID VARCHAR(11),
                             NEW_PESELID VARCHAR(11),
                             OLD_VIP_LEVEL VARCHAR(20),
                             NEW_VIP_LEVEL VARCHAR(20),
                             PRIMARY KEY (EVENT_ID)
);

DELIMITER $$

CREATE TRIGGER READERS_INSERT AFTER INSERT ON READERS                                        -- [1]
    FOR EACH ROW
BEGIN
    INSERT INTO READERS_AUD (EVENT_DATE, EVENT_TYPE, READER_ID, NEW_FIRSTNAME, NEW_LASTNAME, NEW_PESELID, NEW_VIP_LEVEL)                                -- [3]
        VALUE(CURTIME(), "INSERT", NEW.READER_ID, NEW.FIRSTNAME, NEW.LASTNAME, NEW.PESELID, NEW.VIP_LEVEL);                                                           -- [5]
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER READERS_DELETE AFTER DELETE ON READERS                                        -- [1]
    FOR EACH ROW
BEGIN
    INSERT INTO READERS_AUD (EVENT_DATE, EVENT_TYPE, READER_ID, OLD_FIRSTNAME, OLD_LASTNAME, OLD_PESELID, OLD_VIP_LEVEL)                                -- [3]
        VALUE(CURTIME(), "DELETE", OLD.READER_ID, OLD.FIRSTNAME, OLD.LASTNAME, OLD.PESELID, OLD.VIP_LEVEL);                                                           -- [5]
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER READERS_UPDATE AFTER UPDATE ON READERS                                        -- [1]
    FOR EACH ROW
BEGIN
    INSERT INTO READERS_AUD (EVENT_DATE, EVENT_TYPE, READER_ID, OLD_FIRSTNAME, NEW_FIRSTNAME, OLD_LASTNAME, NEW_LASTNAME, OLD_PESELID, NEW_PESELID, OLD_VIP_LEVEL, NEW_VIP_LEVEL)                                -- [3]
        VALUE(CURTIME(), "UPDATE", OLD.READER_ID, OLD.FIRSTNAME, NEW.FIRSTNAME, OLD.LASTNAME, NEW.LASTNAME, OLD.PESELID, NEW.PESELID, OLD.VIP_LEVEL, NEW.VIP_LEVEL);                                                           -- [5]
END $$

DELIMITER ;
