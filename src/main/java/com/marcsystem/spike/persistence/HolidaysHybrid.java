package com.marcsystem.spike.persistence;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity(name = "holidays_hybrid")
@TypeDefs({
                  @TypeDef(name = "string-array", typeClass = StringArrayType.class),
                  @TypeDef(name = "int-array", typeClass = IntArrayType.class),
                  @TypeDef(name = "json", typeClass = JsonStringType.class),
                  @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
          })
public class HolidaysHybrid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String province;

    private int year;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String json;

}
