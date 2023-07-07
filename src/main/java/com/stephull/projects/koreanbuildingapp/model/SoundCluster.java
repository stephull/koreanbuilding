package com.stephull.projects.koreanbuildingapp.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public abstract class SoundCluster<T extends Jamo<?>> {
    
    private Map<T, String> cluster;

    public SoundCluster() {
        cluster = new HashMap<T, String>();
        generate();
    }

    protected abstract JamoIterator<T> getIterator();

    protected void generate() {
        JamoIterator<T> iterator = getIterator();
        while (iterator.hasNext()) {
            T type = iterator.next();
            String sound = iterator.getSound();
            cluster.put(type, sound);
            System.out.println("Key: " + type + "\nValue: " + cluster.get(type) + "\n");
        }
    }

    public void addMapping(T type, String sound) {
        cluster.put(type, sound);
    }

    public String getSoundValue(T type) {
        return cluster.get(type);
    }

    public boolean containsType(T type) {
        return cluster.containsKey(type);
    }

    public void removeMapping(T type) {
        cluster.remove(type);
    }

}

class VowelCluster extends SoundCluster<Vowel> {

    public VowelCluster() {
        super();
    }

    @Override
    protected JamoIterator<Vowel> getIterator() {
        return new VowelIterator();
    }

    private class VowelIterator implements JamoIterator<Vowel> {
        private int currentIndex;
        private List<Vowel> vowels;

        public VowelIterator() {
            currentIndex = 0;
            vowels = Vowel.getAllVowels();
        }

        public boolean hasNext() {
            return currentIndex < vowels.size();
        }

        public Vowel next() {
            Vowel v = vowels.get(currentIndex++);
            return v;
        }

        public String getSound() {
            Vowel v = vowels.get(currentIndex - 1);
            return v.getValue();
        }
    }

}

class ConstCluster extends SoundCluster<Const> {

    public ConstCluster() {
        super();
    }

    @Override
    protected JamoIterator<Const> getIterator() {
        return new ConstIterator();
    }

    private class ConstIterator implements JamoIterator<Const> {
        private int currentIndex;
        private List<Const> consts;

        public ConstIterator() {
            currentIndex = 0;
            consts = Const.getAllConsonants();
        }

        public boolean hasNext() {
            return currentIndex < consts.size();
        }

        public Const next() {
            Const c = consts.get(currentIndex++);
            return c;
        }

        public String getSound() {
            Const c = consts.get(currentIndex - 1);
            return c.getValue();
        }
    }

}

class EndConstCluster extends SoundCluster<EndConst> {

    public EndConstCluster() {
        super();
    }

    @Override
    protected JamoIterator<EndConst> getIterator() {
        return new EndConstIterator();
    }

    private class EndConstIterator implements JamoIterator<EndConst> {
        private int currentIndex;
        private List<EndConst> consts;

        public EndConstIterator() {
            currentIndex = 0;
            consts = EndConst.getAllEndingConsonants();
        }

        public boolean hasNext() {
            return currentIndex < consts.size();
        }

        public EndConst next() {
            EndConst c = consts.get(currentIndex++);
            return c;
        }

        public String getSound() {
            EndConst c = consts.get(currentIndex - 1);
            return c.getValue();
        }
    }

}

class SoundClusterDemo {
    public static void main(String[] args) {
        new VowelCluster();   
        new ConstCluster();
        new EndConstCluster();     
    }
}