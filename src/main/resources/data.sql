INSERT INTO roles (name)
SELECT 'Admin' FROM DUAL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='Admin' LIMIT 1);

INSERT INTO roles (name)
SELECT 'Owner' FROM DUAL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='Owner' LIMIT 1);

INSERT INTO roles (name)
SELECT 'Renter' FROM DUAL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='Renter' LIMIT 1);

INSERT INTO roles (name)
SELECT 'Guest' FROM DUAL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='Guest' LIMIT 1);

-- USER

-- INSERT INTO users (id, user_name, email, first_name, last_name, password, phone_number)
-- VALUES (null, 'admin', 'admin@miu.edu', 'John', 'Smith', '$2a$10$zRLQjacElrjmGJouWg4G0ehtg4oYZ88RtORarMtuxuWCvo122a3iK','641-234-5679');

INSERT INTO users (user_name, email, first_name, last_name, password, phone_number)
VALUES ( 'owner', 'owner@miu.edu', 'Dana', 'Ali', '$2a$10$h2DNVT4FN9vC6fuc8By1huiF7qGUMrpJqC6oGUqD4oYi7mEHfc2Ly','441-134-5660');

INSERT INTO users (user_name, email, first_name, last_name, password, phone_number)
VALUES ('renter', 'renter@miu.edu', 'Renter', 'Renter','$2a$10$jcatEpTX1.L3Gq3KIyE/E.d3FzViiNkyKY9dl62GUmhqgzM.FzgI6', '941-234-0023');

-- User_Role

INSERT INTO user_role (user_id, role_id)
values ((SELECT user_id from users where user_id = 1), (SELECT role_id from roles where role_id = 1));

INSERT INTO user_role (user_id, role_id)
values ((SELECT user_id from users where user_id = 2), (SELECT role_id from roles where role_id = 1));

INSERT INTO user_role (user_id, role_id)
values ((SELECT user_id from users where user_id = 3), (SELECT role_id from users where role_id = 1));

-- Address

INSERT INTO address ( city, state, street_name, zip)
VALUES ('Fairfield', 'Iowa', '1000N 4th Street', '52557');

INSERT INTO address (city, state, street_name, zip)
VALUES ( 'Oakland', 'California', '398N Street', '62557');

INSERT INTO address ( city, state, street_name, zip)
VALUES ( 'Burlington', 'Iowa', '5000E Street', '32557');

INSERT INTO address (city, state, street_name, zip)
VALUES ( 'Iowa city', 'Iowa', '4000W Street', '32557');

-- Legal Entity

INSERT INTO legal_entity (legal_entity_type, first_name, last_name, address_id, user_id, legal_entity_name,phone_number, company_name)
VALUES ('company','', '',
           (SELECT address_id from address where address_id = 1), (SELECT user_id from users where user_id = 1), 'Blue Sky Realty', '941-234-3423', 'Blue Sky Realty');

INSERT INTO legal_entity (legal_entity_type, first_name, last_name, address_id, user_id, legal_entity_name,phone_number, company_name)
VALUES ( 'person', 'Noah', 'Johns', (SELECT address_id from address where address_id = 2), (SELECT user_id from users where user_id = 1) , 'Noah Johns', '441-234-9823', '');

INSERT INTO legal_entity ( legal_entity_type, first_name, last_name, address_id, user_id, legal_entity_name,phone_number, company_name)
VALUES ( 'company', '', '', (SELECT address_id from address where address_id = 3), (SELECT user_id from users where user_id = 2), 'Landmark Realty Group', '441-234-2345', 'Landmark Realty Group');

-- Admin

-- INSERT INTO admin (id, name, legal_entity_id)
-- VALUES (null, 'John Smith', 1);

-- Tenant

