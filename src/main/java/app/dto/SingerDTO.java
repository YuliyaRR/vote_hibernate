package app.dto;

import java.util.Objects;

public class SingerDTO {
    private String name;
    private Integer id;

    public SingerDTO(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public SingerDTO(String name) {
        this.name = name;
    }

    public SingerDTO() {
    }

    public SingerDTO(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingerDTO singerDTO = (SingerDTO) o;
        return Objects.equals(id, singerDTO.id) && Objects.equals(name, singerDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "id = " + id + " ->  name = " + name;
    }
}
