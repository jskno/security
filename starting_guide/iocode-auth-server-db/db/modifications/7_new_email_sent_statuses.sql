INSERT INTO events_statuses (code, description)
VALUES
    ('IN_PROCESS_EMAIL_SENT', 'State of a message that has a batch with format error as response and an email is successfully sent'),
    ('PROCESSED_EMAIL_SENT', 'State of a message that has a communications with format as response and an email is successfully sent');