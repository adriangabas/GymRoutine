package dev.adriangabas.gymroutine.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "ejercicios")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres.")
    @Column(nullable = false, unique = true, length = 150)
    private String nombre;

    // Descripción
    @NotBlank(message = "La descripción es obligatoria.")
    @Lob
    @Column(nullable = false)
    private String descripcion;

    // Ejecución
    @NotBlank(message = "La ejecución es obligatoria.")
    @Lob
    @Column(nullable = false)
    private String ejecucion;

    // Errores frecuentes
    @Lob
    @Column(name = "errores_frecuentes")
    private String erroresFrecuentes;

    // Material
    @Size(max = 150, message = "El material no puede superar los 150 caracteres.")
    @Column(length = 150)
    private String material;

    // Músculo principal
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "musculo_principal_id", nullable = false)
    private GrupoMuscular musculoPrincipal;

    // Imagen
    @URL(message = "La URL de la imagen no es válida.")
    @Size(max = 500, message = "La URL de la imagen no puede superar los 500 caracteres.")
    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    // Vídeo
    @URL(message = "La URL del vídeo no es válida.")
    @Size(max = 500, message = "La URL del vídeo no puede superar los 500 caracteres.")
    @Column(name = "video_url", length = 500)
    private String videoUrl;

    public Ejercicio() {

    }
    public Ejercicio(
            String nombre,
            String descripcion,
            String ejecucion,
            String erroresFrecuentes,
            String material,
            GrupoMuscular musculoPrincipal,
            String imagenUrl,
            String videoUrl
    ){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ejecucion = ejecucion;
        this.erroresFrecuentes = erroresFrecuentes;
        this.material = material;
        this.musculoPrincipal = musculoPrincipal;
        this.imagenUrl = imagenUrl;
        this.videoUrl = videoUrl;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(String ejecucion) {
        this.ejecucion = ejecucion;
    }

    public String getErroresFrecuentes() {
        return erroresFrecuentes;
    }

    public void setErroresFrecuentes(String erroresFrecuentes) {
        this.erroresFrecuentes = erroresFrecuentes;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public GrupoMuscular getMusculoPrincipal() {
        return musculoPrincipal;
    }

    public void setMusculoPrincipal(GrupoMuscular musculoPrincipal) {
        this.musculoPrincipal = musculoPrincipal;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
