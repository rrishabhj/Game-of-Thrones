package es.npatarino.android.gotchallenge.data;

import java.util.Iterator;
import java.util.List;

import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.GoTHouse;
import es.npatarino.android.gotchallenge.domain.repository.GotCharacterRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Antonio López.
 */
public class GotCharacterRepositoryImp implements GotCharacterRepository {

    private OkHttpClient client;
    private String endPoint;
    private final GotCharacterJsonMapper characterJsonMapper;

    public GotCharacterRepositoryImp(OkHttpClient client, String endPoint, GotCharacterJsonMapper jsonMapper) {
        this.client = client;
        this.endPoint = endPoint;
        this.characterJsonMapper = jsonMapper;
    }

    @Override
    public List<GoTCharacter> getList() throws Exception {
        StringBuffer response = getCharactersFromUrl(endPoint);

        return characterJsonMapper.transformList(response.toString());
    }

    protected StringBuffer getCharactersFromUrl(String endPoint) throws Exception {
        Request request = new Request.Builder()
                .url(endPoint)
                .build();

        Response response = client.newCall(request).execute();
        return new StringBuffer(response.body().string());
    }

    @Override
    public GoTCharacter read(GoTCharacter entity) throws Exception {
        List<GoTCharacter> characters = getList();
        int index =  characters.indexOf(entity);
        return index == -1? null :  characters.get(index);
    }

    @Override
    public List<GoTCharacter> read(GoTHouse house) throws Exception{
        List<GoTCharacter> characters = getList();
        Iterator<GoTCharacter> iterator = characters.iterator();
        while (iterator.hasNext()){
            GoTCharacter character = iterator.next();
            if (!character.getHouseId().equals(house.getHouseId())){
                iterator.remove();
            }
        }
        return characters;
    }

}
