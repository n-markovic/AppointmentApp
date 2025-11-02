# Doctor Appointment Application
Mala i jednostavna Spring Boot aplikacija za upravljanje terminima kod doktora.

## Kratak opis sistema
Aplikacija omogućava zakazivanje i pregled termina. Svaki termin ima status koji može biti:
- PENDING (na čekanju)
- CONFIRMED (potvrđen)
- REJECTED (odbijen)

Aplikacija koristi JPA entitete za `Appointment`, `Doctor` i `Patient`. REST API je izložen pod putanjom `/appointments`.

## Funkcionalnosti
- Kreiranje zahteva za termin (POST `/appointments`) koristeći.
- Listanje svih termina (GET `/appointments`).
- Filtriranje po statusu (GET `/appointments?status=CONFIRMED` ili GET `/appointments/CONFIRMED` ili GET `/appointments/status/CONFIRMED`).

## Primeri HTTP zahteva
Testiranje zahteva obavljeno je pomocu POSTMAN programa 
- Pregled svih termina obavlja se pomocu GET metode pri cemu se unosi url: 

```
http://localhost:8080/appointments
```


- Takodje postoji i opcija filtriranja svih termina prema statusu (PENDING, CONFIRMED, REJECTED) sto se obavlja GET metodom na adresu:

```bash
http://localhost:8080/appointments/<status>
```
gde je na mesto statusa potrebno staviti neki od ponudjenih stanja po kojem ce onda termini biti filtrirani.


- Zakazivanje termina obavlja se pomocu POST metode na adresu:

```bash
http://localhost:8080/appointments
```
a u body polju se unosi sledeci json:

```json
{
  "doctorId": 1,
  "patientFirstName": "Pera",
  "patientLastName": "Peric",
  "appointmentDate": "2025-11-10",
  "appointmentTime": "09:30"
}
```

Polja:
- `doctorId` (Long) — ID postojeće osobe doktora.
- `patientFirstName`, `patientLastName` (String) — podaci pacijenta.
- `appointmentDate` (String) — datum u formatu `YYYY-MM-DD`.
- `appointmentTime` (String) — vreme u formatu `HH:mm`.
