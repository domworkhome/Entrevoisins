package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * Get neighbours' list
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * Get detail sheet of a neighbour
     */
    @Override
    public void neighbourGetDetail(Neighbour neighbour){
        neighbours.indexOf(neighbour); }

    /**
     * Delete a neighbour
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * Add a neighbour to favorite list
     */
    @Override
    public void addNeighbourToFavorite(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavorite(true);
    }

    /**
     * Delete a neighbour from favorite list
     */
    @Override
    public void deleteNeighbourFromFavorite(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavorite(false);
    }

    /**
     * Get favorite list with favorites neighbours
     */
    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favNeighbourList = new ArrayList<>();
        for (Neighbour x : neighbours){
            if (x.isFavorite()){
                favNeighbourList.add(x);
            }
        }
        return favNeighbourList;
    }
}
