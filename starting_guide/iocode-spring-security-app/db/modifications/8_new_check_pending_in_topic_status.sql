INSERT INTO events_statuses (code, description)
VALUES
    ('CHECK_PENDING_IN_TOPIC', 'State of a message that has been sent and has a batch code and is in check-pending topic to process the batch result')
ON CONFLICT DO NOTHING;
