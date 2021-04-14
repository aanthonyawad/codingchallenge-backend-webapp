
CREATE TABLE declaration (
    declaration_id VARCHAR(255) NOT NULL,
    patent_id VARCHAR(255),
    standard_id VARCHAR(255) NOT NULL
);

ALTER TABLE declaration ADD CONSTRAINT
"FKGTN626HEP85KWVBJE2XYJQMHT"
FOREIGN KEY(patent_id)
REFERENCES patent ( publication_number );

ALTER TABLE declaration ADD CONSTRAINT
"FK1NYB0QX5TDXGGVU16OW1J5FUH"
FOREIGN KEY ( standard_id )
REFERENCES standard ( standard_id );