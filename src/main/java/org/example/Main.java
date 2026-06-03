package org.example;

import org.example.dao.AnsattDAO;
import org.example.dao.AvdelingDAO;
import org.example.dao.ProsjektDAO;
import org.example.dao.ProsjektdeltagelseDAO;
import org.example.model.Ansatt;
import org.example.model.Avdeling;
import org.example.model.Prosjekt;
import org.example.model.Prosjektdeltagelse;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AnsattDAO ansDao = new AnsattDAO();
        AvdelingDAO avdDao = new AvdelingDAO();
        ProsjektDAO proDao = new ProsjektDAO();
        ProsjektdeltagelseDAO prodeDao = new ProsjektdeltagelseDAO();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\n--- MENY ---");
            System.out.println("1. Finn ansatt (ID)");
            System.out.println("2. List alle ansatte");
            System.out.println("3. Legg til ansatt");
            System.out.println("4. Oppdater ansatt");
            System.out.println("5. Finn ansatt (brukernavn)");
            System.out.println("6. Finn avdeling (ID)");
            System.out.println("7. Sett ansatt til avdeling");
            System.out.println("8. Vis ansatt med avdeling");
            System.out.println("9. Liste ansatte i avdeling");
            System.out.println("10. Endre avdeling for ansatt");
            System.out.println("11. Opprett ny avdeling");
            System.out.println("12. Opprett prosjekt");
            System.out.println("13. Legg ansatt til prosjekt");
            System.out.println("14. Vis prosjekter for ansatt");
            System.out.println("15. Vis ansatte i prosjekt");
            System.out.println("16. Oppdater timer");
            System.out.println("0. Avslutt");
            System.out.print("Velg: ");

            int valg = scanner.nextInt();

            switch (valg) {
                case 1:
                    System.out.print("Skriv inn ansatt-id: ");
                    int id = scanner.nextInt();
                    System.out.println(ansDao.finnAnsattMedId(id));
                    break;

                case 2:
                    List<Ansatt> ansatte = ansDao.hentAlleAnsatte();
                    ansatte.forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("--- Legg til ansatt ---");

                    System.out.print("Brukernavn: ");
                    scanner.nextLine(); // rydder buffer
                    String brukernavnNy = scanner.nextLine();

                    System.out.print("Fornavn: ");
                    String fornavn = scanner.nextLine();

                    System.out.print("Etternavn: ");
                    String etternavn = scanner.nextLine();

                    System.out.print("Stilling: ");
                    String stillingNy = scanner.nextLine();

                    System.out.print("Månedslønn: ");
                    double lonnNy = scanner.nextDouble();

                    System.out.print("Avdeling ID: ");
                    int avdIdNy = scanner.nextInt();

                    Avdeling avdNy = avdDao.finnAvdelingMedId(avdIdNy);

                    if (avdNy == null) {
                        System.out.println("Ugyldig avdeling!");
                        break;
                    }

                    Ansatt ny = new Ansatt();
                    ny.setBrukernavn(brukernavnNy);
                    ny.setFornavn(fornavn);
                    ny.setEtternavn(etternavn);
                    ny.setStilling(stillingNy);
                    ny.setManedslonn(lonnNy);
                    ny.setAnsettelsesdato(java.time.LocalDate.now());

                    ny.setAvdeling(avdNy); //viktig

                    ansDao.lagreAnsatt(ny);

                    System.out.println("Ansatt lagt til!");
                    break;

                case 4:
                    System.out.print("ID: ");
                    int oppId = scanner.nextInt();

                    System.out.print("Ny stilling: ");
                    scanner.nextLine(); // rydder buffer
                    String stilling = scanner.nextLine();

                    System.out.print("Ny lønn: ");
                    double lønn = scanner.nextDouble();

                    Ansatt a = ansDao.finnAnsattMedId(oppId);

                    if (a != null) {
                        a.setStilling(stilling);
                        a.setManedslonn(lønn);
                        ansDao.oppdaterAnsatt(a);
                    } else {
                        System.out.println("Fant ikke ansatt");
                    }
                    break;

                case 5:
                    System.out.print("Skriv inn brukernavn: ");
                    scanner.nextLine(); // rydder buffer
                    String brukernavn = scanner.nextLine();

                    Ansatt funnet = ansDao.finnAnsattMedBrukernavn(brukernavn);

                    if (funnet != null) {
                        System.out.println(funnet);
                    } else {
                        System.out.println("Fant ikke ansatt");
                    }
                    break;

                case 6:
                    System.out.print("Skriv inn avdeling-id: ");
                    int avdId = scanner.nextInt();

                    System.out.println(avdDao.finnAvdelingMedId(avdId));
                    break;

                case 7:
                    System.out.println("--- Sett ansatt til avdeling ---");

                    System.out.print("Ansatt ID: ");
                    int aId = scanner.nextInt();

                    System.out.print("Avdeling ID: ");
                    int avId = scanner.nextInt();

                    // Sjekk at begge finnes
                    Ansatt ansatt = ansDao.finnAnsattMedId(aId);
                    Avdeling avd = avdDao.finnAvdelingMedId(avId);

                    if (ansatt == null) {
                        System.out.println("Fant ikke ansatt!");
                    } else if (avd == null) {
                        System.out.println("Fant ikke avdeling!");
                    } else {
                        ansDao.settAnsattTilAvdeling(aId, avId);
                        System.out.println("Ansatt koblet til avdeling!");
                    }

                    break;

                case 8:
                    System.out.println("--- Vis ansatt med avdeling ---");

                    System.out.print("Ansatt ID: ");
                    int testId = scanner.nextInt();

                    Ansatt ansattTest = ansDao.finnAnsattMedId(testId);

                    if (ansattTest != null) {
                        System.out.println("Navn: " + ansattTest.getFornavn() + " " + ansattTest.getEtternavn());

                        if (ansattTest.getAvdeling() != null) {
                            System.out.println("Avdeling: " + ansattTest.getAvdeling().getNavn());
                        } else {
                            System.out.println("Ingen avdeling");
                        }
                    } else {
                        System.out.println("Fant ikke ansatt");
                    }
                    break;

                case 9:
                    System.out.println("--- Ansatte i avdeling ---");

                    System.out.print("Avdeling ID: ");
                    int avdIdListe = scanner.nextInt();

                    Avdeling avdListe = avdDao.finnAvdelingMedId(avdIdListe);

                    if (avdListe == null) {
                        System.out.println("Fant ikke avdeling");
                        break;
                    }

                    List<Ansatt> ansatteListe = avdListe.getAnsatte();

                    if (ansatteListe.isEmpty()) {
                        System.out.println("Ingen ansatte");
                    } else {
                        ansatteListe.forEach(x ->
                                System.out.println(x.getFornavn() + " " + x.getEtternavn())
                        );
                    }

                    break;

                case 10:
                    System.out.println("--- Endre avdeling ---");

                    System.out.print("Ansatt ID: ");
                    int ansattIdEndre = scanner.nextInt();

                    System.out.print("Ny avdeling ID: ");
                    int nyAvdId = scanner.nextInt();

                    Ansatt ansattEndre = ansDao.finnAnsattMedId(ansattIdEndre);
                    Avdeling nyAvd = avdDao.finnAvdelingMedId(nyAvdId);

                    if (ansattEndre == null || nyAvd == null) {
                        System.out.println("Ugyldig input!");
                        break;
                    }

                    Avdeling gammelAvd = ansattEndre.getAvdeling();

                    if (gammelAvd != null &&
                            gammelAvd.getSjefId() != null &&
                            gammelAvd.getSjefId().equals(ansattEndre.getAnsattId())) {

                        System.out.println("Kan ikke bytte avdeling – ansatt er sjef!");
                        break;
                    }

                    ansattEndre.setAvdeling(nyAvd);
                    ansDao.oppdaterAnsatt(ansattEndre);

                    System.out.println("Avdeling oppdatert!");
                    break;

                case 11:
                    System.out.println("--- Opprett avdeling ---");

                    System.out.print("Navn: ");
                    scanner.nextLine();
                    String avdelingNavn = scanner.nextLine();

                    System.out.print("Sjef (ansatt ID): ");
                    int sjefId = scanner.nextInt();

                    avdDao.opprettAvdeling(avdelingNavn, sjefId);
                    break;

                case 12:
                    System.out.println("--- Opprett prosjekt ---");
                    System.out.print("Navn: ");
                    scanner.nextLine();
                    String prosjektNavn = scanner.nextLine();

                    System.out.print("Beskrivelse: ");
                    String beskrivelse = scanner.nextLine();

                    Prosjekt prosjekt = new Prosjekt(prosjektNavn, beskrivelse);
                    proDao.lagreProsjekt(prosjekt);

                    System.out.println("Prosjekt opprettet!");
                    break;

                case 13:
                    System.out.println("--- Legg ansatt til prosjekt ---");

                    System.out.print("Ansatt ID: ");
                    int ansattId = scanner.nextInt();

                    System.out.print("Prosjekt ID: ");
                    int prosjektId = scanner.nextInt();
                    scanner.nextLine(); // viktig!

                    System.out.print("Rolle: ");
                    String rolle = scanner.nextLine();

                    prodeDao.leggTilAnsattIProsjekt(ansattId, prosjektId, rolle);
                    break;

                case 14:
                    System.out.print("Ansatt ID: ");
                    int proAnsattId = scanner.nextInt();

                    List<Prosjektdeltagelse> prosjekter =
                            prodeDao.finnProsjekterForAnsatt(proAnsattId);

                    if (prosjekter == null || prosjekter.isEmpty()) {
                        System.out.println("Ingen prosjekter funnet.");
                    } else {
                        for (Prosjektdeltagelse pd : prosjekter) {
                            System.out.println(
                                    "Prosjekt: " + pd.getProsjekt().getNavn() +
                                            ", Rolle: " + pd.getRolle() +
                                            ", Timer: " + pd.getTimer()
                            );
                        }
                    }
                    break;

                case 15:
                    System.out.print("Prosjekt ID: ");
                    int proId = scanner.nextInt();

                    List<Prosjektdeltagelse> ansatteIPro =
                            prodeDao.finnAnsatteForProsjekt(proId);

                    if (ansatteIPro == null || ansatteIPro.isEmpty()) {
                        System.out.println("Ingen ansatte funnet.");
                    } else {
                        for (Prosjektdeltagelse pd : ansatteIPro) {
                            System.out.println(
                                    "Ansatt: " + pd.getAnsatt().getFornavn() + " " + pd.getAnsatt().getEtternavn() +
                                            ", Rolle: " + pd.getRolle() +
                                            ", Timer: " + pd.getTimer()
                            );
                        }
                    }
                    break;

                case 16:
                    System.out.print("Ansatt ID: ");
                    int ansId = scanner.nextInt();

                    System.out.print("Prosjekt ID: ");
                    int pId = scanner.nextInt();

                    System.out.print("Nye timer: ");
                    int timer = scanner.nextInt();

                    try {
                        prodeDao.oppdaterTimer(ansId, pId, timer);
                        System.out.println("Timer oppdatert!");
                    } catch (Exception e) {
                        System.out.println("Fant ikke deltagelse!");
                    }
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Ugyldig valg");
            }
        }
        scanner.close();

    }
}