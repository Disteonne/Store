package com.netcracker.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "info")//list
    @Type(type = "jsonb")
    private List<Product> history; //List<Product> id and so on...

    @OneToOne(mappedBy = "history")
    @JsonIgnore
    private User user;

}
