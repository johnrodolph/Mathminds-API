package com.IT334G4.Mathminds.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.IT334G4.Mathminds.OtherClasses.PracticeQA;

@Entity
@Table(name = "practice_qa_mapping")
public class PracticeQaMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; // Assuming there's an id column or create one

    @ManyToOne
    @JoinColumn(name = "practice_id", nullable = false)
    PracticeEntity practice;

    @ManyToOne
    @JoinColumn(name = "practice_qa_practiceqa_id", nullable = false)
    PracticeQA practiceQA;

    @Column(name = "order_index")
    int orderIndex;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public PracticeEntity getPractice() {
        return practice;
    }

    public PracticeQA getPracticeQA() {
        return practiceQA;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPractice(PracticeEntity practice) {
        this.practice = practice;
    }

    public void setPracticeQA(PracticeQA practiceQA) {
        this.practiceQA = practiceQA;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}