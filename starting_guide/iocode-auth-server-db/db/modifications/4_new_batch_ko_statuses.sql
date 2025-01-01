INSERT INTO events_statuses (code, description)
VALUES
    ('BATCH_KO', 'State of a message that has been sent and has a format error as response'),
    ('IN_PROCESS_KO', 'Final state of a message that has a stored batch with format error with an email sent successfully');