/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package color.grid.count;

import java.util.*;

/**
 * @author Banuka
 */
public class ColorGridCount {


    public static void main(String[] args) {

        //Define Grid Size
        int COLUMNS = 6;
        int ROWS = 5;

        ColorGridCount colorGridCount = new ColorGridCount();
        colorGridCount.init(COLUMNS, ROWS);

        //Print generated grid
        colorGridCount.printGrid();

        //Find largest connected block
        Block block = colorGridCount.getLargestBlock();

        if (block != null) {
            System.out.println("==========================");

            //Print largest connected block with grid
            colorGridCount.printGridWithBlock(block);
        }
    }

    //Define Colors
    public static final char[] COLORS = {'R', 'B', 'W', 'G'};

    //Memory to store nodes
    private HashMap<Position, ColorNode> colorNodes;

    private int col;
    private int row;

    public void init(int cols, int rows) {

        colorNodes = new HashMap<>();

        this.col = cols;
        this.row = rows;

        Random random = new Random();

        //Arsing random colors to the grid
        for (int i = 0; i < cols * rows; i++) {
            int column = i % cols;
            int row = (int) Math.floor(i / cols);
            this.colorNodes.put(new Position(column, row), new ColorNode(i, column, row, COLORS[random.nextInt(3)]));
        }
    }

    //Return node according to position
    public ColorNode getNode(int column, int row) {
        return this.colorNodes.get(new Position(column, row));
    }

    public void printGrid() {
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                if (x == this.col - 1) {
                    System.out.println(getNode(x, y).getColor());
                } else {
                    System.out.print(getNode(x, y).getColor() + ", ");
                }
            }
        }
    }

    //Print functionality to print largest connected block with grid
    public void printGridWithBlock(Block block) {
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                ColorNode n = getNode(x, y);
                String color = block.hasNode(n) ?  new StringBuilder().append(n.getColor()).append('+').toString() : new StringBuilder().append(n.getColor()).toString();
                if (x == this.col - 1) {
                    System.out.println(color);
                } else {
                    System.out.print(color + ", ");
                }
            }
        }
    }

    public List<ColorNode> findNearColorNodes(ColorNode node, Block block) {

        List<ColorNode> nodes = new ArrayList<>();

        Position position = node.getPosition();
        ColorNode upNode = this.colorNodes.get(position.up());
        if (upNode != null && upNode.getColor() == node.getColor() && !block.hasNode(upNode)) {
            nodes.add(upNode);
        }
        ColorNode downNode = this.colorNodes.get(position.down());
        if (downNode != null && downNode.getColor() == node.getColor() && !block.hasNode(downNode)) {
            nodes.add(downNode);
        }
        ColorNode leftNode = this.colorNodes.get(position.left());
        if (leftNode != null && leftNode.getColor() == node.getColor() && !block.hasNode(leftNode)) {
            nodes.add(leftNode);
        }
        ColorNode rightNode = this.colorNodes.get(position.right());
        if (rightNode != null && rightNode.getColor() == node.getColor() && !block.hasNode(rightNode)) {
            nodes.add(rightNode);
        }
        return nodes;
    }

    public Block getContinuousBlock(int column, int row) {

        Position position = new Position(column, row);
        ColorNode startNode = this.colorNodes.get(position);
        Block block = new Block(startNode.getColor());
        block.addNode(startNode);

        LinkedList<ColorNode> nodesToVisit = new LinkedList<>();
        nodesToVisit.addAll(findNearColorNodes(startNode, block));

        while (!nodesToVisit.isEmpty()) {
            ColorNode nextNode = nodesToVisit.remove();
            block.addNode(nextNode);
            nodesToVisit.addAll(findNearColorNodes(nextNode, block));
        }

        return block;
    }

    public Block getLargestBlock() {

        Set<Position> allPositions = new LinkedHashSet<>(this.colorNodes.keySet());
        List<Block> allBlocks = new ArrayList<>();

        for (Position position: allPositions) {
            Block newBlock = getContinuousBlock(position.getColumn(), position.getRow());
            allBlocks.add(newBlock);
        }

        //sorting block according to size
        Collections.sort(allBlocks);

        return allBlocks.size() > 0 ? allBlocks.get(0) : null;
    }


}
