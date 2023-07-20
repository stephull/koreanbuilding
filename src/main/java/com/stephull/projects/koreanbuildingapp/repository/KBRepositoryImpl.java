package com.stephull.projects.koreanbuildingapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;
import com.stephull.projects.koreanbuildingapp.model.KoreanPronunciation;

public class KBRepositoryImpl implements KBRepository {

    private final KBRepository kbrepo;

    public KBRepositoryImpl(KBRepository kbrepo) {
        this.kbrepo = kbrepo;
    }

    @Override
    public <S extends KoreanBuild> S insert(S entity) {
        return kbrepo.insert(entity);
    }

    @Override
    public <S extends KoreanBuild> List<S> insert(Iterable<S> entities) {
        return kbrepo.insert(entities);
    }

    @Override
    public <S extends KoreanBuild> List<S> findAll(Example<S> example) {

        return kbrepo.findAll(example);
    }

    @Override
    public <S extends KoreanBuild> List<S> findAll(Example<S> example, Sort sort) {

        return kbrepo.findAll(example, sort);
    }

    @Override
    public <S extends KoreanBuild> List<S> saveAll(Iterable<S> entities) {

        return kbrepo.saveAll(entities);
    }

    @Override
    public List<KoreanBuild> findAll() {

        return kbrepo.findAll();
    }

    @Override
    public List<KoreanBuild> findAllById(Iterable<String> ids) {

        return kbrepo.findAllById(ids);
    }

    @Override
    public <S extends KoreanBuild> S save(S entity) {

        return kbrepo.save(entity);
    }

    @Override
    public Optional<KoreanBuild> findById(String id) {

        return kbrepo.findById(id);
    }

    @Override
    public boolean existsById(String id) {

        return kbrepo.existsById(id);
    }

    @Override
    public long count() {

        return kbrepo.count();
    }

    @Override
    public void deleteById(String id) {

        kbrepo.deleteById(id);
    }

    @Override
    public void delete(KoreanBuild entity) {

        kbrepo.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {

        kbrepo.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends KoreanBuild> entities) {

        kbrepo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

        kbrepo.deleteAll();
    }

    @Override
    public List<KoreanBuild> findAll(Sort sort) {

        return kbrepo.findAll(sort);
    }

    @Override
    public Page<KoreanBuild> findAll(Pageable pageable) {

        return kbrepo.findAll(pageable);
    }

    @Override
    public <S extends KoreanBuild> Optional<S> findOne(Example<S> example) {

        return kbrepo.findOne(example);
    }

    @Override
    public <S extends KoreanBuild> Page<S> findAll(Example<S> example, Pageable pageable) {

        return kbrepo.findAll(example, pageable);
    }

    @Override
    public <S extends KoreanBuild> long count(Example<S> example) {

        return kbrepo.count(example);
    }

    @Override
    public <S extends KoreanBuild> boolean exists(Example<S> example) {

        return kbrepo.exists(example);
    }

    @Override
    public <S extends KoreanBuild, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {

        return kbrepo.findBy(example, queryFunction);
    }

    // at this point, every method here is not directly from MongoRepository

    @Override
    public String findUnicodeByBuild(String build) {

        return kbrepo.findUnicodeByBuild(build);
    }

    @Override
    public List<KoreanBuild> findDictionaryByBuild(String build) {

        return kbrepo.findDictionaryByBuild(build);
    }

    @Override
    public String findSoundKeyByBuild(String build) {

        return kbrepo.findSoundKeyByBuild(build);
    }

    @Override
    public KoreanPronunciation findPronunciationByBuild(String build) {

        return kbrepo.findPronunciationByBuild(build);
    }

    @Override
    public double findAppearencesStatsByBuild(String build) {

        return kbrepo.findAppearencesStatsByBuild(build);
    }

    @Override
    public int findFrequencyStatsByBuild(String build) {

        return kbrepo.findFrequencyStatsByBuild(build);
    }

    @Override
    public boolean[] findSpecialSingularPronunciationByBuild(String build) {

        return kbrepo.findSpecialSingularPronunciationByBuild(build);
    }

    @Override
    public List<KoreanBuild> findConsonantsByBuild(String build) {

        return kbrepo.findConsonantsByBuild(build);
    }

    @Override
    public List<KoreanBuild> findVowelsByBuild(String build) {

        return kbrepo.findVowelsByBuild(build);
    }
}
