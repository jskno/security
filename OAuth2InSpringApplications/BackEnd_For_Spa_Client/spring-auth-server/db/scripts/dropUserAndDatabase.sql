
select pg_terminate_backend(pid) from pg_stat_activity where datname='auth-server-db';
DROP DATABASE IF EXISTS auth-server-db;

DROP ROLE IF EXISTS testuser;
