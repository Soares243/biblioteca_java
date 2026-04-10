package br.unisales.database.table;

import java.util.ArrayList;
import java.util.List;

import br.unisales.database.table.primery_key.LivroCategoriaId;
import br.unisales.database.table.primery_key.LivroAutorId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "ano")
    private int ano;

    @Default
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroCategoria> livroCategorias = new ArrayList<>();

    @Default
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroAutor> livroAutores = new ArrayList<>();

    @Default
    @Fetch(FetchMode.SUBSELECT)
    @ElementCollection
    @CollectionTable(name = "livro_palavras_chave", joinColumns = @JoinColumn(name = "livro_isbn"))
    @Column(name = "palavra_chave")
    private List<String> palavrasChave = new ArrayList<>();

    /**
     * @apiNote Adiciona uma categoria ao livro para ser salva no banco de dados
     * @param categoria
     * @author Vito Rodrigues Franzosi
     * @Data Criação 19.03.2026
     */
    public void addCategoria(Categoria categoria) {
        LivroCategoria lc = new LivroCategoria();

        lc.setLivro(this);
        lc.setCategoria(categoria);
        lc.setId(new LivroCategoriaId(this.isbn, categoria.getId()));

        livroCategorias.add(lc);
        categoria.getLivroCategorias().add(lc);
    }

    /**
     * @apiNote Remove uma categoria do livro para ser excluída do banco de dados
     * @param categoria
     * @author Vito Rodrigues Franzosi
     * @Data Criação 19.03.2026
     */
    public void removeCategoria(Categoria categoria) {
        livroCategorias.removeIf(lc -> {
            boolean match = lc.getCategoria().equals(categoria);
            if (match) {
                lc.getCategoria().getLivroCategorias().remove(lc);
                lc.setLivro(null);
                lc.setCategoria(null);
            }
            return match;
        });
    }

    /**
     * @apiNote Adiciona um autor ao livro para ser salvo no banco de dados
     * @param autor
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2026
     */
    public void addAutor(Autor autor) {
        LivroAutor la = new LivroAutor();

        la.setLivro(this);
        la.setAutor(autor);
        la.setId(new LivroAutorId(this.isbn, autor.getId()));

        livroAutores.add(la);
        autor.getLivroAutores().add(la);
    }

    /**
     * @apiNote Remove um autor do livro para ser excluído do banco de dados
     * @param autor
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2026
     */
    public void removeAutor(Autor autor) {
        livroAutores.removeIf(la -> {
            boolean match = la.getAutor().equals(autor);
            if (match) {
                la.getAutor().getLivroAutores().remove(la);
                la.setLivro(null);
                la.setAutor(null);
            }
            return match;
        });
    }    
}