package main.zadanie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                return ((CompositeBlock) block).getBlocks()
                        .stream()
                        .filter(b -> b.getColor()
                                .equals(color))
                        .findFirst();
            }
        }
        return Optional.of(null);
    }


    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> blocks = new ArrayList<>();
        for (Block block : blocks) {
            if (block.getMaterial().equals(material)) {
                blocks.add(block);
            }
            if (block instanceof CompositeBlock) {
                blocks.addAll(((CompositeBlock) block).getBlocks()
                        .stream()
                        .filter(b -> b.getMaterial().equals(material))
                        .toList());
            }
        }
        return blocks;
    }

    @Override
    public int count() {
        int count = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                count += ((CompositeBlock) block).getBlocks().size();
            } else {
                count++;
            }
        }
        return count;
    }
}

