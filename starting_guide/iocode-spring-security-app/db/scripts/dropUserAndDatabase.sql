
select pg_terminate_backend(pid) from pg_stat_activity where datname='spring-sec-db';
DROP DATABASE IF EXISTS spring-sec-db;

DROP ROLE IF EXISTS testuser;
