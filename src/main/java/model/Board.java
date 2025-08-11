package model;


import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static model.GameStarEnum.*;


public class Board {

    private final List<List<Space>> space;


    public Board(List<List<Space>> space) {
        this.space = space;
    }

    public List<List<Space>> getSpace() {
        return space;
    }
    public GameStarEnum getStatus() {
        if (space.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))){
          return  NOW_STARTED;

        }
        return space.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? INCOMPLETE : COMPLETE;

    }
    public boolean hasErrors(){
    if (getStatus() == NOW_STARTED){
    return false;
    }
    return (space.stream().flatMap(Collection::stream).anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected())));

    }

}
