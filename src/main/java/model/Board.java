package model;


import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static model.GameStarEnum.*;


public class Board {

    private final List<List<Space>> spaces;


    public Board(List<List<Space>> space) {
        this.spaces = space;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }


    public GameStarEnum getStatus() {
        if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))){
          return  NOW_STARTED;

        }
        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? INCOMPLETE : COMPLETE;

    }
    public boolean hasErrors(){
    if (getStatus() == NOW_STARTED){
    return false;
    }
    return (spaces.stream().flatMap(Collection::stream)
            .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected())));

    }
    public boolean changeValue(final int col, final int row, final Integer value){
        var space = spaces.get(col).get(row);
        if (space.isFixed()){
            return false;
         }
        space.setActual(value);
        return true;
    }
    public boolean clearValue(final int col, final int row){
        var space = spaces.get(col).get(row);
        if (space.isFixed()){
            return false;
        }
    space.clearSpace();
        return true;
    }

    public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameIsFinished(){
        return !hasErrors() && getStatus().equals(COMPLETE) ;
    }
}
