-- Ticket numbers come from a database sequence so they stay unique
-- no matter how many application instances are running.
CREATE SEQUENCE incident_ticket_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE incident (
    id            uuid         NOT NULL,
    ticket_number varchar(20)  NOT NULL,
    title         varchar(200) NOT NULL,
    description   text         NOT NULL,
    status        varchar(20)  NOT NULL,
    version       bigint       NOT NULL DEFAULT 0,
    created_at    timestamptz  NOT NULL,
    updated_at    timestamptz  NOT NULL,

    CONSTRAINT pk_incident PRIMARY KEY (id),
    CONSTRAINT uq_incident_ticket_number UNIQUE (ticket_number),
    CONSTRAINT ck_incident_status CHECK (status IN
        ('NEW', 'TRIAGED', 'ASSIGNED', 'IN_PROGRESS',
         'RESOLVED', 'CLOSED', 'REOPENED', 'CANCELLED'))
);
