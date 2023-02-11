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

INSERT INTO users (id, user_name, email, first_name, last_name, password, phone_number)
VALUES (null, 'owner', 'owner@miu.edu', 'Dana', 'Ali', '$2a$10$h2DNVT4FN9vC6fuc8By1huiF7qGUMrpJqC6oGUqD4oYi7mEHfc2Ly','441-134-5660');

INSERT INTO users (id, user_name, email, first_name, last_name, password, phone_number)
VALUES (null, 'renter', 'renter@miu.edu', 'Renter', 'Renter','$2a$10$jcatEpTX1.L3Gq3KIyE/E.d3FzViiNkyKY9dl62GUmhqgzM.FzgI6', '941-234-0023');

-- User_Role

INSERT INTO user_role (user_id, role_id)
values (1, 1);

INSERT INTO user_role (user_id, role_id)
values (2, 2);

INSERT INTO user_role (user_id, role_id)
values (3, 3);

-- Address

INSERT INTO address (id, city, state, street_name, zip)
VALUES (null, 'Fairfield', 'Iowa', '1000N 4th Street', '52557');

INSERT INTO address (id, city, state, street_name, zip)
VALUES (null, 'Oakland', 'California', '398N Street', '62557');

INSERT INTO address (id, city, state, street_name, zip)
VALUES (null, 'Burlington', 'Iowa', '5000E Street', '32557');

INSERT INTO address (id, city, state, street_name, zip)
VALUES (null, 'Iowa city', 'Iowa', '4000W Street', '32557');

-- Legal Entity

INSERT INTO legal_entity (id, legal_entity_type, first_name, last_name, address_id, user_id, legal_entity_name,phone_number, company_name)
VALUES (null, 'company', null, null, 1, 1, 'Blue Sky Realty', '941-234-3423', 'Blue Sky Realty');

INSERT INTO legal_entity (id, legal_entity_type, first_name, last_name, address_id, user_id, legal_entity_name,phone_number, company_name)
VALUES (null, 'person', 'Noah', 'Johns', 2,1 , 'Noah Johns', '441-234-9823', null);

INSERT INTO legal_entity (id, legal_entity_type, first_name, last_name, address_id, user_id, legal_entity_name,phone_number, company_name)
VALUES (null, 'company', null, null, 3, 2, 'Landmark Realty Group', '441-234-2345', 'Landmark Realty Group');

-- Admin

-- INSERT INTO admin (id, name, legal_entity_id)
-- VALUES (null, 'John Smith', 1);

-- Tenant

INSERT INTO tenant (id, name, legal_entity_id)
VALUES (null, 'Maria Mike', 1);

INSERT INTO tenant (id, name, legal_entity_id)
VALUES (null, 'Hanna Tesfay', 2);

INSERT INTO tenant (id, name, legal_entity_id)
VALUES (null, 'Annna Mathew', 3);

-- Landlord

INSERT INTO landlord (id, name, legal_entity_id)
VALUES (null, 'Yohannes', 2);

INSERT INTO landlord (id, name, legal_entity_id)
VALUES (null, 'Amelia', 1);

INSERT INTO landlord (id, name, legal_entity_id)
VALUES (null, 'Mike', 2);

-- Property

INSERT INTO property (id, name, property_type, no_of_bath_rooms, no_of_rooms, land_extent, address_id)
VALUES (null, 'house', 'house', 2, 4, 5870, 2);

INSERT INTO property (id, name, property_type, no_of_bath_rooms, no_of_rooms, land_extent, address_id)
VALUES (null, 'apartment', 'apartment', 2, 6, 12870, 3);

INSERT INTO property (id, name, property_type, no_of_bath_rooms, no_of_rooms, land_extent, address_id)
VALUES (null, 'house', 'house', 1, 2, 1870, 1);

INSERT INTO property (id, name, property_type, no_of_bath_rooms, no_of_rooms, land_extent, address_id)
VALUES (null, 'apartment', 'apartment', 2, 6, 12870, 1);

-- Landlord_Property

-- INSERT INTO landlord_property (property_id, landlord_id)
-- VALUES (1, 3);

-- INSERT INTO landlord_property (property_id, landlord_id)
-- VALUES (2, 1);

-- INSERT INTO landlord_property (property_id, landlord_id)
-- VALUES (3, 2);

-- INSERT INTO Landlord_property (property_id, landlord_id)
-- VALUES (4, 3);

-- Tenant_Property

INSERT INTO tenant_property (property_id, tenant_id)
VALUES (1, 2);

-- Rent_Application

INSERT INTO rent_application (id, application_id, status, title, landlord_id, property_id, tenant_id)
VALUES ( null, 'H123', 'approved', 'House Rent Application', 3, 1, 2 );

INSERT INTO rent_application (id, application_id, status, title, landlord_id, property_id, tenant_id)
VALUES ( null, 'A123', 'pending', 'Apartment Rent Application', 1, 2, 3 );

-- Rental_Agreement

INSERT INTO rental_agreement (id, agreement_id, contract, end_date, prepared_date, signed_date, start_date, title,landlord_id, property_id, tenant_id )
VALUES ( null, 'HA234', '', '2024-03-11', '2023-03-09', '2023-03-11', '2023-03-12', 'House Rental Agreement', 3, 1, 2 );