package algo.services.Impl;

import algo.models.Problem;
import algo.models.ProblemData;
import algo.models.UserData;
import algo.repositories.ProblemRepository;
import algo.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AzatYusupov on 26.12.2017.
 */
@Service
public class ProblemServiceImpl implements ProblemService{

    private final int SOLVED_BY_USER = 1;
    private final int UNSOLVED_BY_USER = 2;

    @Autowired
    ProblemRepository problemRepository;

    @Override
    public List<Problem> findAll() {
        return problemRepository.findAll();
    }

    @Override
    public List<String> findAllId() {
        return problemRepository.findAllId();
    }

    @Override
    public Page<Problem> findPage(int page) {
        return problemRepository.findAll(new PageRequest(page-1,
                Problem.PEAR_PAGE, Sort.Direction.ASC, "id"));
    }

    @Override
    public Problem findById(String id) {
        return problemRepository.findOne(id);
    }

    @Override
    public long getCntPages() {
        return (problemRepository.count() + Problem.PEAR_PAGE - 1) / Problem.PEAR_PAGE;
    }

    @Override
    public Page<Problem> findByTitle(String search) {
        return problemRepository.findByTitleLike(search,
                new PageRequest(0, Problem.PEAR_PAGE, Sort.Direction.ASC, "id"));
    }

    @Override
    public List<List<Problem>> preparedGropingProblems(List<Problem> problems, String solvedData, String unsolvedData) {
        int[] solved = new int[50000];
        if (solvedData.length() > 0) {
            String[] solvedProblems = solvedData.substring(1, solvedData.length()-1).split("::");
            for (int i = 0; i < solvedProblems.length; i++) {
                solved[Integer.parseInt(solvedProblems[i])] = SOLVED_BY_USER;
            }
        }
        if (unsolvedData.length() > 0) {
            String[] unsolvedProblems = unsolvedData.substring(1, unsolvedData.length()-1).split("::");
            for (int i = 0; i < unsolvedProblems.length; i++) {
                solved[Integer.parseInt(unsolvedProblems[i])] = UNSOLVED_BY_USER;
            }
        }

        List<List<Problem>> result = new ArrayList<>();
        List<Problem> currentList = new ArrayList<>();
        for (Problem problem : problems) {
            problem.setSolvedByUser(solved[Integer.parseInt(problem.getId())]);
            currentList.add(problem);
            if (currentList.size()== UserData.PROBLEMS_PEAR_ON_PROFILE) {
                result.add(currentList);
                currentList = new ArrayList<>();
            }
        }
        if (!currentList.isEmpty())
            result.add(currentList);
        return result;
    }
}
