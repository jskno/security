CREATE INDEX status_index ON events USING btree (status_id);
CREATE INDEX IF NOT EXISTS idx_events_batch_id_fk ON events (batch_id);
--CREATE INDEX IF NOT EXISTS idx_events_status_id_fk ON events (status_id);