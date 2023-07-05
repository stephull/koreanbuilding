package com.stephull.projects.koreanbuildingapp.model;

public class CustomID {
    
    private String id;

    public CustomID(String id) {
        this.id = id;
    }

    public String getCustomID() {
        return this.id;
    }

    public void setCustomID(String newID) {
        this.id = newID;
    }

}

// for Korean Builds
class KBID extends CustomID {
    public KBID(String id) {
        super(id);
    }
}

// for Korean Speech
class KSID extends CustomID {
    public KSID(String id) {
        super(id);
    }
}

// for Korean Pronunciation
class KPID extends CustomID {
    public KPID(String id) {
        super(id);
    }
}

// for Korean-English Dictionary
class KEDID extends CustomID {
    public KEDID(String id) {
        super(id);
    }
}

// for Chinese Origin
class COID extends CustomID {
    public COID(String id) {
        super(id);
    }
}