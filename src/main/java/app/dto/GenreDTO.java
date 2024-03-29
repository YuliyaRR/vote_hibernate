package app.dto;

import java.util.Objects;

public class GenreDTO {
    private String name;
    private Integer id;

    public GenreDTO(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public GenreDTO() {
    }

    public GenreDTO(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return Objects.equals(id, genreDTO.id) && Objects.equals(name, genreDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "id = " + id + " -> name = " + name;
    }
}
