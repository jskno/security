
-- First Step to go all env and be able to deploy (only add columns)
-- Database version 1.2
ALTER TABLE hospedajes.emails ALTER COLUMN communication_id DROP NOT NULL;
ALTER TABLE hospedajes.emails ADD COLUMN IF NOT EXISTS event_id bigint NULL unique;
ALTER TABLE hospedajes.emails ADD CONSTRAINT fkey_events FOREIGN KEY (event_id) REFERENCES events(id);
