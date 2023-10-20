import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val movieId: Long, // ID do filme associado
    val rating: Int,   // Nota da revisão (por exemplo, de 1 a 5)
    val reviewText: String
)