import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class OnlineCoursesAnalyzer {

    static class OnlineCourse {

        private final String institution;

        private final String courseNumber;

        private final String launchDate;

        private final String courseTitle;

        private final List<String> instructors;

        private final String courseSubject;

        private final int year;

        private final int honorCodeCertificates;

        private final int participants;

        private final int audited;

        private final int certified;

        private final double auditedInPercent;

        private final double certifiedInPercent;

        private final double certifiedOfBiggerThan50InPercent;

        private final double playedVideoInPercent;

        private final double postedInForumInPercent;

        private final double gradeHigherThanZeroInPercent;

        private final double totalCourseHoursInThousands;

        private final double medianHoursForCertification;

        private final double medianAge;

        private final double maleInPercent;

        private final double femaleInPercent;

        private final double bachelorDegreeOrHigherInPercent;

        public String getCourseNumber() {
            return courseNumber;
        }

        public String getLaunchDate() {
            return launchDate;
        }

        public String getCourseTitle() {
            return courseTitle;
        }

        public List<String> getInstructors() {
            return instructors;
        }

        public String getCourseSubject() {
            return courseSubject;
        }

        public int getYear() {
            return year;
        }

        public int getHonorCodeCertificates() {
            return honorCodeCertificates;
        }

        public int getAudited() {
            return audited;
        }

        public int getCertified() {
            return certified;
        }

        public double getAuditedInPercent() {
            return auditedInPercent;
        }

        public double getCertifiedInPercent() {
            return certifiedInPercent;
        }

        public double getCertifiedOfBiggerThan50InPercent() {
            return certifiedOfBiggerThan50InPercent;
        }

        public double getPlayedVideoInPercent() {
            return playedVideoInPercent;
        }

        public double getPostedInForumInPercent() {
            return postedInForumInPercent;
        }

        public double getGradeHigherThanZeroInPercent() {
            return gradeHigherThanZeroInPercent;
        }

        public double getTotalCourseHoursInThousands() {
            return totalCourseHoursInThousands;
        }

        public double getMedianHoursForCertification() {
            return medianHoursForCertification;
        }

        public double getMedianAge() {
            return medianAge;
        }

        public double getMaleInPercent() {
            return maleInPercent;
        }

        public double getFemaleInPercent() {
            return femaleInPercent;
        }

        public double getBachelorDegreeOrHigherInPercent() {
            return bachelorDegreeOrHigherInPercent;
        }

        public String getInstitution() {
            return institution;
        }

        public int getParticipants() {
            return participants;
        }

        public OnlineCourse(String institution, String courseNumber, String launchDate,
                            String courseTitle, String instructors, String courseSubject,
                            int year, int honorCodeCertificates, int participants, int audited,
                            int certified, double auditedInPercent, double certifiedInPercent,
                            double certifiedOfBiggerThan50InPercent, double playedVideoInPercent,
                            double postedInForumInPercent, double gradeHigherThanZeroInPercent,
                            double totalCourseHoursInThousands, double medianHoursForCertification,
                            double medianAge, double maleInPercent, double femaleInPercent,
                            double bachelorDegreeOrHigherInPercent) {
            this.institution = institution;
            this.courseNumber = courseNumber;
            this.launchDate = launchDate;
            this.courseTitle = courseTitle.replace("\"", "");
            this.instructors = Arrays.asList(instructors.replace("\"", "").split(", "));
            this.courseSubject = courseSubject.replace("\"", "");
            this.year = year;
            this.honorCodeCertificates = honorCodeCertificates;
            this.participants = participants;
            this.audited = audited;
            this.certified = certified;
            this.auditedInPercent = auditedInPercent;
            this.certifiedInPercent = certifiedInPercent;
            this.certifiedOfBiggerThan50InPercent = certifiedOfBiggerThan50InPercent;
            this.playedVideoInPercent = playedVideoInPercent;
            this.postedInForumInPercent = postedInForumInPercent;
            this.gradeHigherThanZeroInPercent = gradeHigherThanZeroInPercent;
            this.totalCourseHoursInThousands = totalCourseHoursInThousands;
            this.medianHoursForCertification = medianHoursForCertification;
            this.medianAge = medianAge;
            this.maleInPercent = maleInPercent;
            this.femaleInPercent = femaleInPercent;
            this.bachelorDegreeOrHigherInPercent = bachelorDegreeOrHigherInPercent;
        }
    }

    public static class PreDate {

        double simVal;

        String courseTitle;

        public PreDate(double simVal, String courseTitle) {
            this.simVal = simVal;
            this.courseTitle = courseTitle;
        }

        public double getSimVal() {
            return simVal;
        }

        public String getCourseTitle() {
            return courseTitle;
        }

    }

    public List<OnlineCourse> onlineCourseList = new ArrayList<>();

    public OnlineCoursesAnalyzer(String datasetPath) throws IOException {
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(datasetPath, StandardCharsets.UTF_8));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                OnlineCourse course =
                        new OnlineCourse(info[0], info[1], info[2], info[3], info[4], info[5],
                                Integer.parseInt(info[6]), Integer.parseInt(info[7]),
                                Integer.parseInt(info[8]),
                                Integer.parseInt(info[9]), Integer.parseInt(info[10]),
                                Double.parseDouble(info[11]),
                                Double.parseDouble(info[12]), Double.parseDouble(info[13]),
                                Double.parseDouble(info[14]),
                                Double.parseDouble(info[15]), Double.parseDouble(info[16]),
                                Double.parseDouble(info[17]),
                                Double.parseDouble(info[18]), Double.parseDouble(info[19]),
                                Double.parseDouble(info[20]),
                                Double.parseDouble(info[21]), Double.parseDouble(info[22]));
                onlineCourseList.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public Map<String, Integer> getPtcpCountByInst() {
        Map<String, Integer> pre =
                onlineCourseList.stream().collect(Collectors
                        .groupingBy(OnlineCourse::getInstitution,
                                Collectors.summingInt(OnlineCourse::getParticipants)));
        return pre.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                                .collect((Collectors.toMap(Map.Entry::getKey,
                                        Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)));
    }

    public Map<String, Integer> getPtcpCountByInstAndSubject() {
        Map<String, Integer> pre = new HashMap<>();
        onlineCourseList.forEach(ol -> {
            String temp = ol.getInstitution() + "-" + ol.getCourseSubject();
            if (pre.containsKey(temp)) {
                int val = pre.get(temp);
                pre.remove(temp);
                val += ol.getParticipants();
                pre.put(temp, val);
            } else {
                pre.put(temp, ol.getParticipants());
            }
        });
        return pre.entrySet()
                .stream().sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<String, List<List<String>>> getCourseListOfInstructor() {
        Map<String, List<List<String>>> pre = new HashMap<>();
        onlineCourseList.forEach(ol -> ol.getInstructors().forEach(s -> {
            if (pre.containsKey(s)) {
                if (ol.getInstructors().size() == 1) {
                    pre.get(s).get(0).add(ol.getCourseTitle());
                } else {
                    pre.get(s).get(1).add(ol.getCourseTitle());
                }
            } else {
                List<String> t1 = new ArrayList<>();
                List<String> t2 = new ArrayList<>();
                List<List<String>> t = new ArrayList<>();
                if (ol.getInstructors().size() == 1) {
                    t1.add(ol.getCourseTitle());
                } else {
                    t2.add(ol.getCourseTitle());
                }
                t.add(t1);
                t.add(t2);
                pre.put(s, t);
            }
        }));
        Map<String, List<List<String>>> result = new HashMap<>();
        pre.forEach((key, value) -> {
            List<String> temp1 = value.get(0).stream().distinct().collect(Collectors.toList());
            List<String> temp2 = value.get(1).stream().distinct().collect(Collectors.toList());
            temp1.sort(String::compareTo);
            temp2.sort(String::compareTo);
            List<List<String>> temp = new ArrayList<>();
            temp.add(temp1);
            temp.add(temp2);
            result.put(key, temp);
        });
        return result;
    }

    public List<String> getCourses(int topK, String by) {
        List<String> result;
        if (by.equals("hours")) {
            result = onlineCourseList.stream().sorted(Comparator
                            .comparing(OnlineCourse::getTotalCourseHoursInThousands).reversed())
                            .filter(distinctByKey(OnlineCourse::getCourseTitle)).limit(topK)
                            .map(OnlineCourse::getCourseTitle).collect(Collectors.toList());
        } else if ((by.equals("participants"))) {
            result = onlineCourseList.stream()
                    .sorted(Comparator.comparing(OnlineCourse::getParticipants).reversed())
                            .filter(distinctByKey(OnlineCourse::getCourseTitle)).limit(topK)
                            .map(OnlineCourse::getCourseTitle).collect(Collectors.toList());
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

    public List<String> searchCourses(String courseSubject,
                                      double percentAudited, double totalCourseHours) {
        Predicate<OnlineCourse> test1 = onlineCourse -> onlineCourse
                .getCourseSubject().toLowerCase().contains(courseSubject.toLowerCase());
        Predicate<OnlineCourse> test2 = onlineCourse -> onlineCourse
                .getAuditedInPercent() >= percentAudited;
        Predicate<OnlineCourse> test3 = onlineCourse -> onlineCourse
                .getTotalCourseHoursInThousands() <= totalCourseHours;
        return onlineCourseList.stream().filter(test1).filter(test2).filter(test3)
                .filter(distinctByKey(OnlineCourse::getCourseTitle))
                .map(OnlineCourse::getCourseTitle).sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    public List<String> recommendCourses(int age, int gender, int isBachelorOrHigher) {
        Map<String, List<OnlineCourse>> preMap = onlineCourseList
                .stream().collect(Collectors.groupingBy(OnlineCourse::getCourseNumber));
        Comparator<OnlineCourse> sameCourseRankedByData = (o1, o2) -> {
            String[] date1 = o1.getLaunchDate().split("/");
            String[] date2 = o2.getLaunchDate().split("/");
            String a = date1[2] + date1[0] + date1[1];
            String b = date2[2] + date2[0] + date2[1];
            return a.compareTo(b);
        };
        List<PreDate> preList = new ArrayList<>();
        preMap.forEach((s, onlineCourses) -> {
            double averageMedialAge = onlineCourses.stream()
                    .mapToDouble(OnlineCourse::getMedianAge).average().getAsDouble();
            double averageGenderPercent = onlineCourses.stream()
                    .mapToDouble(OnlineCourse::getMaleInPercent).average().getAsDouble();
            double averageBachelorPercent = onlineCourses.stream()
                    .mapToDouble(OnlineCourse::getBachelorDegreeOrHigherInPercent)
                    .average().getAsDouble();
            String title = Collections.max(onlineCourses, sameCourseRankedByData).getCourseTitle();
            double simVal = Math.pow((age - averageMedialAge), 2)
                    + Math.pow((gender * 100 - averageGenderPercent), 2)
                    + Math.pow((isBachelorOrHigher * 100 - averageBachelorPercent), 2);
            PreDate tempD = new PreDate(simVal, title);
            preList.add(tempD);
        });
        return  preList.stream().sorted(Comparator.comparing(PreDate::getSimVal)
                        .thenComparing(PreDate::getCourseTitle))
                        .filter(distinctByKey(PreDate::getCourseTitle))
                        .limit(10).map(PreDate::getCourseTitle).collect(Collectors.toList());
    }

}
