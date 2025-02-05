SET search_path TO auth;

insert into auth.clients (client_id, client_name, client_secret, grant_type, redirect_uri, require_proof_key, token_format, id)
values ('client', 'myAuthCodeClient', 'secret', 'authorization_code', 'http://localhost:8081', false, 'self_contained', 'c20bfc7b-20e2-40cb-9b17-5e7c994d4288');

insert into auth.clients (client_id, client_name, client_secret, grant_type, redirect_uri, require_proof_key, token_format, id)
values ('client-pkce', 'myAuthCodeClientPKCE', 'secret-pkce', 'authorization_code', 'http://localhost:8082', true, 'self_contained', '017ba212-0512-4917-8b94-53ee16e27395');

insert into auth.clients (client_id, client_name, client_secret, grant_type, redirect_uri, require_proof_key, token_format, id)
values ('client-optk', 'myAuthCodeClientOPTK', 'secret-optk', 'authorization_code', 'http://localhost:8083', true, 'reference', 'fd17988a-df94-4d49-abf7-2011f31c6d45');
