import org.springframework.data.repository.CrudRepository

interface MovieRepository : CrudRepository<Movie, Long>