\connect auth-server-db

CREATE SCHEMA IF NOT EXISTS hospedajes;
SET search_path TO hospedajes;

CREATE SEQUENCE revinfo_seq START WITH 1 INCREMENT BY 50;

-- revinfo definition
CREATE TABLE revinfo (
    rev integer not null,
    revtstmp bigint,
    CONSTRAINT revinfo_pkey PRIMARY KEY (rev)
);

-- events_origins definition
CREATE TABLE events_origins (
    id serial NOT NULL,
    "name" varchar(3) NOT NULL,
    email_sender varchar(8000) NOT NULL,
    email_cc varchar(8000) NOT NULL,
    CONSTRAINT events_origins_pkey PRIMARY KEY (id),
    CONSTRAINT uk_name UNIQUE (name)
);

-- events_statuses definition
CREATE TABLE events_statuses (
    id serial NOT NULL,
    code varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    CONSTRAINT events_statuses_pkey PRIMARY KEY (id),
    CONSTRAINT uk_status UNIQUE (code)
);

-- batches definition
CREATE TABLE batches (
     id bigserial NOT NULL,
     batch_code varchar(36) NULL,
     code integer NULL,
     description varchar(255) NULL,
     created_at timestamptz NOT NULL,
     CONSTRAINT batches_pkey PRIMARY KEY (id),
     CONSTRAINT uk_batch_code UNIQUE (batch_code)
);

-- events definition
CREATE TABLE events (
    id bigserial NOT NULL,
    origin varchar(255) NOT NULL,
    operation varchar(255) NOT NULL,
    booking_number varchar(255) NOT NULL,
    is_rental_agreement boolean NOT NULL,
    checkout_station_code varchar(255) NOT NULL,
    checkout_station_email varchar(255) NULL,
    body text NOT NULL,
    partition integer NOT NULL,
    offset_ bigint NOT NULL,
    status_id integer NOT NULL,
    batch_id bigint NULL,
    retry boolean NOT NULL,
    retries integer NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NULL,
    CONSTRAINT events_pkey PRIMARY KEY (id),
    CONSTRAINT uk_events_key UNIQUE (origin, booking_number, is_rental_agreement, offset_, partition)
);
CREATE INDEX booking_index ON events USING btree (origin, booking_number, is_rental_agreement);
CREATE INDEX IF NOT EXISTS idx_events_batch_id_fk ON events (batch_id);
--CREATE INDEX IF NOT EXISTS idx_events_status_id_fk ON events (status_id);
CREATE INDEX status_index ON events USING btree (status_id);

ALTER TABLE events
    ADD CONSTRAINT fkn_events_statuses FOREIGN KEY (status_id) REFERENCES events_statuses(id),
    ADD CONSTRAINT fkn_batches FOREIGN KEY (batch_id) REFERENCES batches(id);

-- event_statuses_history definition
CREATE TABLE event_statuses_history (
    id bigserial NOT NULL,
    event_id bigint NOT NULL,
    status_id integer NOT NULL,
    created_at timestamptz NOT NULL,
    CONSTRAINT event_statuses_history_pkey PRIMARY KEY (id)
);

-- event_statuses_history foreign keys
ALTER TABLE event_statuses_history
    ADD CONSTRAINT fk_events FOREIGN KEY (event_id) REFERENCES events(id),
    ADD CONSTRAINT fk_events_statuses FOREIGN KEY (status_id) REFERENCES events_statuses(id);

-- notifications_errors definition
CREATE TABLE notifications_errors (
    id bigserial NOT NULL,
    event_id bigint NOT NULL,
    error_code varchar(255) NOT NULL,
    error_description text NULL,
    created_at timestamptz NOT NULL,
    CONSTRAINT notifications_errors_pkey PRIMARY KEY (id)
);

ALTER TABLE notifications_errors
    ADD CONSTRAINT fk_events FOREIGN KEY (event_id) REFERENCES events(id);

-- batches_responses definition
CREATE TABLE batches_responses (
     id bigserial NOT NULL,
     batch_id bigint NOT NULL,
     request_date timestamptz NULL,
     processing_date timestamptz NULL,
     status_code integer NULL,
     status_description varchar(255) NULL,
     created_at timestamptz NOT NULL,
     CONSTRAINT batches_responses_pkey PRIMARY KEY (id),
     CONSTRAINT uk_batch_id UNIQUE (batch_id)
);

-- batches_responses foreign keys
ALTER TABLE batches_responses
    ADD CONSTRAINT fkey_batches FOREIGN KEY (batch_id) REFERENCES batches(id);

-- batches_responses_aud definition
CREATE TABLE batches_responses_aud (
    rev integer not null,
    revtype smallint,
    id bigint NOT NULL,
    batch_id bigint NOT NULL,
    request_date timestamptz NULL,
    processing_date timestamptz NULL,
    status_code integer NULL,
    status_description varchar(255) NULL,
    CONSTRAINT batches_responses_aud_pkey PRIMARY KEY (rev, id)
);

-- batches_responses_aud foreign keys
ALTER TABLE batches_responses_aud
    ADD CONSTRAINT fkey_revinfo FOREIGN KEY (rev) REFERENCES revinfo(rev);

-- communications definition
CREATE TABLE communications (
    id bigserial NOT NULL,
    batch_response_id bigint NOT NULL,
    event_id bigint NOT NULL,
    code varchar(36) NULL,
    position integer NULL,
    cancelled boolean NULL,
    error_type varchar(255) NULL,
    error text NULL,
    created_at timestamptz NOT NULL,
    CONSTRAINT communications_pkey PRIMARY KEY (id),
    CONSTRAINT uk_event_id UNIQUE (event_id),
    CONSTRAINT uk_communication_code UNIQUE (code)
);

-- communications foreign keys
ALTER TABLE communications
    ADD CONSTRAINT fkey_batches_responses FOREIGN KEY (batch_response_id) REFERENCES batches_responses(id),
    ADD CONSTRAINT fkey_events FOREIGN KEY (event_id) REFERENCES events(id);

-- communications_aud definition
CREATE TABLE communications_aud (
    rev integer not null,
    revtype smallint,
    id bigint NOT NULL,
    batch_response_id bigint NOT NULL,
    event_id bigint NOT NULL,
    code varchar(36) NULL,
    position integer NULL,
    cancelled boolean NULL,
    error_type varchar(255) NULL,
    error varchar(255) NULL,
    CONSTRAINT communications_aud_pkey PRIMARY KEY (rev, id)
);

-- communications_aud foreign keys
ALTER TABLE communications_aud
    ADD CONSTRAINT fkey_revinfo FOREIGN KEY (rev) REFERENCES revinfo(rev);

-- emails definition
CREATE TABLE emails (
    id bigserial NOT NULL,
    event_id bigint NOT NULL UNIQUE,
    notification_email_id varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NULL,
    CONSTRAINT emails_pkey PRIMARY KEY (id),
    CONSTRAINT uk_notification_email_id UNIQUE (notification_email_id)
);

-- emails foreign keys
ALTER TABLE emails ADD CONSTRAINT fkey_events FOREIGN KEY (event_id) REFERENCES events(id);

-- shedlock definition
CREATE TABLE shedlock (
      name varchar(64) NOT NULL,
      lock_until TIMESTAMP NOT NULL,
      locked_at TIMESTAMP NOT NULL,
      locked_by varchar(255) NOT NULL,
      CONSTRAINT shedlock_pk PRIMARY KEY (name)
);
