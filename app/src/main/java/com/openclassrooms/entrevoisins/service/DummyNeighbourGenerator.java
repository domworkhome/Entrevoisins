package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint Pierre du mont à 5km","+33 6 86 57 90 14", "caroline", "Bonjour! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner! J'aime les jeux de cartes tels la belote et le tarot."),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "Mont-de-Marsan","+33 7 54 97 82 14", "Jack", "Salut ! Fan de Harley Davidson et rocker dans l'âme je cherche des compagnons de route pour faire des virées folles !"),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "Saint-Perdon", "+33 6 82 52 14 73", "Chloé", "Bonjour je suis Personal Shopper et je vous propose mes services pour mettre à jour votre garde robe !"),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "Laglorieuse", "+33 6 09 12 65 49", "Vincent", "On dit de moi que je suis un hipster au grand coeur et à l'oeil aguerri et c'est vrai ! J'adore la photographie depuis toujours et j'ai d'ailleurs démarré une activité freelance de photographe. Je recherche actuellement des modèles pour faire des shootings indoor et outdoor. Si vous êtes intéressé(e) pour m'aider à développer mon portfolio et profiter de photos pros (je peux éventuellement vous faire un petit \"book\") n'hésitez plus et contactez-moi par mon lien Facebook."),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "Artassenx", "+33 7 82 50 64 93", "Elodie", "Je suis Elodie, je suis mannequin et je recherche des contrats"),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c","Mazerolles", "+33 6 29 39 47 82 ", "Sylvain", "Je recherche un guitariste pour monter un groupe de musique (je suis chanteur)."),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d","Bascons", "+33 6 14 15 33 67", "Laetitia", "Je propose mes services de Dogsitter. Appelez-moi ou envoyez-moi un message pour connaître mes conditions"),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b","Bougue", "+33 6 89 50 21 97", "Dan", "Passionné de littérature je souhaite intégrer un club de lecture"),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704g","Mont-de-Marsan", "+33 6 48 84 98 38", "Joseph", "Développeur web fullstack à votre service !"),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d","Aurice", "+33 7 98 85 54 42", "Emma", "Coiffeuse à domicile"),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d","Laglorieuse", "+33 6 66 69 83 24", "Patrick", "Prof de théatre je monte bientôt un spectacle et cherche des figurants"),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d","Saint-Pierre-du-Mont", "+33 6 15 33 58 94", "Ludovic", "Fan de musique electro je cherche quelqu'un pour m'emmener en Belgique à Boom au Tomorrowland")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
