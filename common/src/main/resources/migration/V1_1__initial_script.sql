create table c_cars
(
	id bigserial not null
		constraint c_cars_pkey
			primary key,
	brand varchar(50) not null,
	model varchar(50) not null,
	year_of_issue bigint not null,
	color varchar(20) not null,
	vin varchar(20) not null,
	registration_plate varchar(10) not null,
	number_of_seats bigint not null,
	rate double precision not null,
	created timestamp(6) default CURRENT_TIMESTAMP not null,
	changed timestamp(6) default CURRENT_TIMESTAMP not null,
	is_deleted boolean default false not null
);

alter table c_cars owner to test;

create index c_cars_brand_index
	on c_cars (brand);

create index c_cars_color_index
	on c_cars (color);

create index c_cars_model_index
	on c_cars (model);

create index c_cars_number_of_seats_index
	on c_cars (number_of_seats);

create index c_cars_rate_index
	on c_cars (rate);

create index c_cars_year_of_issue_index
	on c_cars (year_of_issue);

create unique index c_cars_registration_plate_uindex
	on c_cars (registration_plate);

create unique index c_cars_vin_uindex
	on c_cars (vin);

create index c_cars_created_index
	on c_cars (created desc);

create index c_cars_is_deleted_index
	on c_cars (is_deleted);

create table c_clients
(
	id bigserial not null
		constraint c_clients_pkey
			primary key,
	name varchar(50) not null,
	surname varchar(50) not null,
	gender varchar(10) not null,
	passport varchar(20) not null,
	country varchar(20) not null,
	telephone varchar(15) not null,
	"e-mail" varchar(50) not null,
	login varchar(20) not null,
	password varchar(30) not null,
	created timestamp(6) default CURRENT_TIMESTAMP not null,
	changed timestamp(6) default CURRENT_TIMESTAMP not null,
	is_deleted boolean default false not null,
	birth_date date
);

alter table c_clients owner to test;

create index c_clients_gender_index
	on c_clients (gender);

create index c_clients_name_index
	on c_clients (name);

create index c_clients_surname_index
	on c_clients (surname);

create unique index "c_clients_e-mail_uindex"
	on c_clients ("e-mail");

create unique index c_clients_login_uindex
	on c_clients (login);

create unique index c_clients_passport_uindex
	on c_clients (passport);

create unique index c_clients_telephone_uindex
	on c_clients (telephone);

create index c_clients_created_index
	on c_clients (created desc);

create index c_clients_is_deleted_index
	on c_clients (is_deleted);

create index c_clients_country_index
	on c_clients (country);

create index c_clients_birth_date_index
	on c_clients (birth_date);

create table c_requests
(
	id bigserial not null
		constraint c_requests_pk
			primary key,
	id_car bigint not null
		constraint c_requests_c_cars_id_fk
			references c_cars
				on update cascade on delete cascade,
	start_date_and_time timestamp(6) not null,
	end_date_and_time timestamp(6) not null
);

alter table c_requests owner to test;

create index c_requests_end_date_and_time_index
	on c_requests (end_date_and_time);

create index c_requests_start_date_and_time_index
	on c_requests (start_date_and_time);

create index c_requests_id_car_index
	on c_requests (id_car);

create unique index c_requests_id_uindex
	on c_requests (id);

create table c_renting
(
	id bigserial not null
		constraint c_renting_pk
			primary key,
	cost double precision not null,
	created timestamp(6) default CURRENT_TIMESTAMP not null,
	changed timestamp(6) default CURRENT_TIMESTAMP not null,
	is_deleted boolean default false not null
);

alter table c_renting owner to test;

create index c_renting_cost_index
	on c_renting (cost);

create index c_renting_created_index
	on c_renting (created desc);

create index c_renting_is_deleted_index
	on c_renting (is_deleted);

create table c_transfers
(
	id bigserial not null
		constraint c_transfers_pk
			primary key,
	id_renting bigint not null
		constraint c_transfers_c_renting_id_fk
			references c_renting
				on update cascade on delete cascade,
	start_place varchar(50) not null,
	end_place varchar(50) not null,
	start_odometer_reading double precision not null,
	end_odometer_reading double precision not null,
	start_amount_of_fuel double precision not null,
	end_amount_of_fuel double precision not null
);

alter table c_transfers owner to test;

create unique index c_transfers_id_renting_uindex
	on c_transfers (id_renting);

create index c_transfers_end_amount_of_fuel_index
	on c_transfers (end_amount_of_fuel);

create index c_transfers_end_odometer_reading_index
	on c_transfers (end_odometer_reading);

create index c_transfers_end_place_index
	on c_transfers (end_place);

create index c_transfers_start_amount_of_fuel_index
	on c_transfers (start_amount_of_fuel);

create index c_transfers_start_odometer_reading_index
	on c_transfers (start_odometer_reading);

create index c_transfers_start_place_index
	on c_transfers (start_place);

create unique index c_transfers_id_uindex
	on c_transfers (id);

create table c_repairing
(
	id bigserial not null
		constraint c_repairing_pk
			primary key,
	damage varchar(100),
	cost double precision,
	id_client bigint not null
		constraint c_repairing_c_clients_id_fk
			references c_clients
				on update cascade on delete cascade
);

alter table c_repairing owner to test;

create index c_repairing_cost_index
	on c_repairing (cost);

create unique index c_repairing_id_uindex
	on c_repairing (id);

create index c_repairing_id_client_index
	on c_repairing (id_client);

