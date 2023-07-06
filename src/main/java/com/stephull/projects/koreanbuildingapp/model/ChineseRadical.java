package com.stephull.projects.koreanbuildingapp.model;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ChineseRadicals")
public class ChineseRadical {

    @Id
    private String id;

    private CustomID<ChineseRadical> crid;
    private String radical;
    private String unicode;
    private Optional<String> basic;

    public ChineseRadical() {}

    public ChineseRadical(
        String radical,
        String unicode
    ) {
        this.radical = radical;
        this.unicode = unicode;
    }

    public ChineseRadical(
        String radical,
        String unicode,
        Optional<String> basic
    ) {
        this.radical = radical;
        this.unicode = unicode;
        this.basic = basic;
    }

    public String getId() {
        return this.id;
    }

    public String getCrId() {
        return this.crid.getCustomID();
    }

    public void setCrId(String newCrId) {
        this.crid.setCustomID(newCrId);
    }

    public String getRadical() {
        return this.radical;
    }

    public void setRadical(String newRadical) {
        this.radical = newRadical;
    }

    public String getUnicode() {
        return this.unicode;
    }

    public void setUnicode(String newUnicode) {
        this.unicode = newUnicode;
    }

    public String getBasic() {
        return this.basic.orElse("");
    }

    public void setBasic(Optional<String> newBasic) {
        this.basic = newBasic;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Radical=%s
                Unicode=%s
                Basic form=%s
            ]
            """,
            radical, unicode, basic.orElse("None")
        );
    }
}
