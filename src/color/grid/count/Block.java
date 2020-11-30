/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package color.grid.count;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Banuka
 */
class Block implements Comparable<Block> {

    private char color;

    private Set<ColorNode> nodes;

    public Block(char color) {
        this.color = color;
        nodes = new HashSet<>();
    }

    public Set<Position> allPositions() {
        return nodes.stream().map(n -> n.getPosition()).collect(Collectors.toSet());
    }

    public boolean addNode(ColorNode node) {
        if (node != null && !nodes.contains(node)
                && node.getColor() == this.color) {
            return nodes.add(node);
        }
        return false;
    }

    public boolean hasNode(ColorNode node) {
        if (node == null)
            return false;
        return nodes.stream().anyMatch(n -> n.getId() == node.getId());
    }

    public int size() {
        return nodes.size();
    }


    @Override
    public int compareTo(Block o) {
        return o.size() - this.size();
    }
}