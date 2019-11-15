import Results.FinalResult;
import Results.GetResult;
import Results.Search;
import UrlResults.SingleUrlInfo;
import UrlResults.UrlSearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ClubSearcher {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {

        ClubSearcher clubSearcher = new ClubSearcher();
        while (true) {
            System.out.println("Enter the name of the football club you are searching for (q to quit):");
            Scanner scanner = new Scanner(System.in);
            String clubName = scanner.nextLine();
            if (clubName.equals("q")) break;
            LinkedList<FinalResult> results;
            try {
                results = clubSearcher.findClubWikiUrl(clubName);
                clubSearcher.printResults(results);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printResults(List<FinalResult> finalResults) {
        System.out.println("*********************");
        System.out.println("Results:");
        for (FinalResult finalResult : finalResults) {
            System.out.println("---------------------");
            System.out.println("On page " + finalResult.getQueryPage() + " of the query, I found " + finalResult.getPageTitle());
            System.out.println("(URL): " + finalResult.getPageUrl());
        }
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("The most accurate result:");
        FinalResult finalResult = finalResults.get(0);
        System.out.println(finalResult.getPageTitle());
        System.out.println("(URL): " + finalResult.getPageUrl());
        System.out.println("*********************");
        System.out.println("*********************");
    }

    private String listUrlBuilder(String clubName, int pageNumber) {

        String offset = (pageNumber > 0)
                ? "sroffset=" + (pageNumber * 10)
                : "";
        return "https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srsearch="
                + clubName
                + "&srlimit=10&srprop=snippet&"
                + offset
                + "&srinfo=";
    }

    private String urlGetterUrlBuilder(Long pageId) {
        return "https://en.wikipedia.org/w/api.php?action=query&prop=info&pageids="
                + pageId
                + "&inprop=url&format=json";
    }

    private LinkedList<FinalResult> findClubWikiUrl(String clubName) throws Exception {

        LinkedList<FinalResult> finalResults = new LinkedList<>();
        HashSet<String> articleTitlesFound = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            String url = listUrlBuilder(clubName, i);
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            GetResult getResult = objectMapper.readValue(response.body(), GetResult.class);
            long pageId = 0;
            try {
                pageId = getBestResultPageId(getResult);
            } catch (NullPointerException ex) {
                System.out.println("Page " + (i + 1) + ": No club information found on this page");
                continue;
            }
            if (pageId == 0) continue;
            SingleUrlInfo pageInfo = getPageUrlAndTitle(pageId);
            if (!articleTitlesFound.contains(pageInfo.getTitle())) {
                FinalResult finalResult = FinalResult.builder()
                        .pageTitle(pageInfo.getTitle())
                        .queryPage(i + 1)
                        .pageUrl(pageInfo.getFullurl())
                        .build();
                finalResults.add(finalResult);
                articleTitlesFound.add(pageInfo.getTitle());
            }
        }
        if (finalResults.size() > 0) {
            return finalResults;
        } else {
            throw new Exception("No results found");
        }
    }

    private SingleUrlInfo getPageUrlAndTitle(long pageId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlGetterUrlBuilder(pageId)))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        UrlSearchResult getResult = objectMapper.readValue(response.body(), UrlSearchResult.class);
        return getResult.getQuery().getPages().get(Long.toString(pageId));
    }

    private long getBestResultPageId(GetResult getResult) throws NullPointerException {
        for (Search search : getResult.getQuery().getSearch()) {
            try {
                if (search.getTitle().toLowerCase().contains("football club")) {
                    return search.getPageid();
                }
                if (search.getSnippet().toLowerCase().contains("football club")) {
                    return search.getPageid();
                }
                if (search.getTitle().toLowerCase().contains("club")) {
                    return search.getPageid();
                }
                if (search.getSnippet().toLowerCase().contains("club")) {
                    return search.getPageid();
                }
            } catch (NullPointerException ignored) {
            }
        }
        throw new NullPointerException();
    }
}

