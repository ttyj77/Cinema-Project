package Controller;

import Model.FilmDto;

import java.util.ArrayList;

public class FilmController {

    private ArrayList<FilmDto> list;
    private int nextId;


    public FilmController() {
        list = new ArrayList<>();
        nextId = 1;

        FilmDto filmDto = new FilmDto();
        filmDto.setTitle("반지의제왕");
        filmDto.setRating("12");
        filmDto.setSummary("모든 힘을 지배할 악의 군주 ‘사우론’의 절대반지가 깨어나고 악의 세력이 세상을 지배해가며 중간계는 대혼란에 처한다.");
        add(filmDto);

        FilmDto filmDto1 = new FilmDto();
        filmDto.setTitle("아바타 2 물의길");
        filmDto.setRating("12");
        filmDto.setSummary("판도라 행성에서 '제이크 설리'와 '네이티리'가 이룬 가족이 겪게 되는 무자비한 위협과 살아남기 위해 떠나야 하는 긴 여정");
        add(filmDto);

        FilmDto filmDto2 = new FilmDto();
        filmDto.setTitle("해리 포터와 마법사의 돌");
        filmDto.setRating("15");
        filmDto.setSummary("해리 포터(다니엘 래드클리프 분)는 위압적인 버논 숙부(리챠드 그리피스 분)와 냉담한 이모 페투니아 (피오나 쇼 분), 욕심 많고 버릇없는 사촌 더즐리(해리 멜링 분) 밑에서 갖은 구박을 견디며 계단 밑 벽장에서 생활한다. ");
        add(filmDto);

        FilmDto filmDto3 = new FilmDto();
        filmDto.setTitle("장화신은 고양이: 끝내주는 모험");
        filmDto.setRating("19");
        filmDto.setSummary("아홉 개의 목숨 중 단 하나의 목숨만 남은 장화신은 고양이. 마지막 남은 목숨을 지키기 위해 히어로의 삶 대신 반려묘의 삶을 선택한 그에게 찾아온 마지막 기회,");
        add(filmDto);

    }

    public void add(FilmDto filmDto) {
        filmDto.setId(nextId++);
        list.add(filmDto);
    }

    //모든 영화 리스트
    public ArrayList<FilmDto> selectAll() {
        ArrayList<FilmDto> temp = new ArrayList<>();
        for (FilmDto b : list) {
            temp.add(new FilmDto(b));
        }
        return list;
    }

    //영화 개별 검색
    public FilmDto selectOne(int index) {
        FilmDto temp = new FilmDto(index);
        if (list.contains(temp)) {
            return new FilmDto(list.get(list.indexOf(temp)));
        }
        return null;
    }

    public void update(FilmDto filmDto) {
        list.set(list.indexOf(filmDto), filmDto);
    }

    public void delete(int id) {
        list.remove(new FilmDto(id));
    }

}
