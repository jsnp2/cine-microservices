package br.com.caelum.cine.session.shared;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovieSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private MovieTheater movieTheater;
    private ZonedDateTime date;

    @Enumerated(EnumType.STRING)
    private MovieSessionStatus status = MovieSessionStatus.OPEN;

    /**
     * @deprecated frameworks eyes
     */
    @Deprecated
    MovieSession() {
    }

    public MovieSession(Movie movie, MovieTheater movieTheater, ZonedDateTime date) {
        this.movie = movie;
        this.movieTheater = movieTheater;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public MovieTheater getMovieTheater() {
        return movieTheater;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public ZonedDateTime getForecastOfEnd() {
        return date.plus(movie.getDuration());
    }

    public void start() {
        status = status.start();
    }

    public void finish() {
        status = status.finish();
    }

    public void cancel() {
        status = status.cancel();
    }
}
