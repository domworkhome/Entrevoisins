package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get detail from a neighbour
     * @param neighbour
     */
    void neighbourGetDetail (Neighbour neighbour);

    /**
     * Delete a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * add a selected neighbour to favorite list
     * @param neighbour
     */
    void addNeighbourToFavorite(Neighbour neighbour);

    /**
     * delete a selected neighbour from favorite list
     * @param neighbour
     */
    void deleteNeighbourFromFavorite(Neighbour neighbour);

    /**
     * get favorite's list
     * @return
     */
    List<Neighbour> getFavorites();
}
