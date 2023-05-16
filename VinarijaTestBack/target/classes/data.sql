
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

              
INSERT INTO tip (id, ime) VALUES (1, 'Belo vino');
INSERT INTO tip (id, ime) VALUES (2, 'Roze vino');
INSERT INTO tip (id, ime) VALUES (3, 'Crno vino');

INSERT INTO vinarija (id, godina_osnivanja, ime) VALUES (1, 1999, 'Ogijeva vinarija');
INSERT INTO vinarija (id, godina_osnivanja, ime) VALUES (2, 1998, 'Darkova vinarija');
INSERT INTO vinarija (id, godina_osnivanja, ime) VALUES (3, 1997, 'Lukina vinarija');

INSERT INTO vino (id, broj_flasa, cena_flase, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (1, 20, 99.99, 2005, 'Sardone', 'Lepo vino', 1, 1);
INSERT INTO vino (id, broj_flasa, cena_flase, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (2, 9, 199.99, 2005, 'Sardone2', 'Ruzno vino', 2, 2);
INSERT INTO vino (id, broj_flasa, cena_flase, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (3, 22, 299.99, 2005, 'Sardone3', 'Onako vino', 3, 3);
INSERT INTO vino (id, broj_flasa, cena_flase, godina_proizvodnje, ime, opis, tip_id, vinarija_id) VALUES (4, 3, 399.99, 2005, 'Sardone4', 'Ekstra vino', 1, 2);
