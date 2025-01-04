
-- Second step this whole script has been launched by DBA in all environments
-- Database version 1.3

UPDATE hospedajes.emails em SET event_id = (select event_id from communications c where c.id = em.communication_id), communication_id = null
WHERE event_id is null;

ALTER TABLE hospedajes.emails DROP CONSTRAINT uk_communication_id;
ALTER TABLE hospedajes.emails DROP COLUMN communication_id;
ALTER TABLE hospedajes.emails ALTER COLUMN event_id SET NOT NULL

