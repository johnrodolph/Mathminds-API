package com.IT334G4.Mathminds.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tblLesson")
public class LessonEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lesson_id")
	private int lid;

	@Column(name = "lesson_title")
	private String ltitle;

	@Column(name = "lesson_topic")
    private String ltopic;

    public LessonEntity() {
        super();
    }

    public LessonEntity(int lid, String ltitle, String ltopic) {
        this.lid = lid;
        this.ltitle = ltitle;
        this.ltopic = ltopic;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getLtopic() {
        return ltopic;
    }

    public void setLtopic(String ltopic) {
        this.ltopic = ltopic;
    }

    
    

}
