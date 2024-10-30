CREATE TABLE PazhouhandeRah_db.impression_event (
                             impressionId VARCHAR(500) NOT NULL PRIMARY KEY,
                             appId INT,
                             countryCode VARCHAR(2),
                             advertiserId INT
);

CREATE TABLE PazhouhandeRah_db.click_event (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        impressionId VARCHAR(500) NOT NULL,
                        revenue DOUBLE
);
