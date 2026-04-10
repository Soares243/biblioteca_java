package br.unisales.database.table.primery_key;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LivroAutorId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "livro_isbn", nullable = false, length = 20)
    private String livroIsbn;

    @Column(name = "autor_id", nullable = false)
    private Integer autorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroAutorId that = (LivroAutorId) o;
        return Objects.equals(livroIsbn, that.livroIsbn) && Objects.equals(autorId, that.autorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livroIsbn, autorId);
    }
}
