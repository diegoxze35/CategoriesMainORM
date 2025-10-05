# This is a simple Java application that uses the Hibernate ORM to perform some CRUD operations with a Java Swing UI.

### The Entities
#### Category
```java
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategory;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private LocalDateTime createdAt;

}
```
#### Event
```java
@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEvent;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private LocalDate eventDate;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "category_event")
	private Category category;

}
```
### The Dao interface and implementation
```java
public interface Dao<ENTITY, ID> extends Closeable {
	List<ENTITY> findAll();
	ENTITY findById(ID id);
	ENTITY save(ENTITY entity);
	void delete(ENTITY entity);
}
```

```java
public class EventDao implements Dao<Event, Integer> {

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;

	public EventDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("data");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public List<Event> findAll() {
		String jpql = "SELECT c FROM Event c";
		TypedQuery<Event> query = entityManager.createQuery(jpql, Event.class);
		return query.getResultList();
	}

	@Override
	public Event findById(Integer id) {
		return entityManager.find(Event.class, id);
	}

	@Override
	public Event save(Event entity) {
		entityManager.getTransaction().begin();
		Event newEntity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return newEntity;
	}

	@Override
	public void delete(Event entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
}

```

### The UI
<img width="983" height="692" alt="image" src="https://github.com/user-attachments/assets/0cfabd8e-726a-4ef1-ace9-2e0cf46116ec" />
