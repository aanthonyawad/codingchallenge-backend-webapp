create table patent (
    publication_number VARCHAR(255) NOT NULL PRIMARY KEY,
    publication_date DATE NOT NULL,
    title VARCHAR(255) NOT NULL
);

create table standard (
    standard_id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description LONGTEXT NOT NULL
);
