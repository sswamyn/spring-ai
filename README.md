# spring-ai
Notes from session by Ken Kousen 

Useful links 
- Ken's Spring AI example: https://github.com/kousen/springaiexamples 
- OpenAIClient https://github.com/kousen/OpenAIClient
- Official Spring project: https://github.com/spring-projects
- Spring AI Documentation: https://docs.spring.io/spring-ai/reference/index.html





Participant 3285 
Presenter
7:17AM
jvmArgs = listOf("-Xshare:off", "-XX:+EnableDynamicAgentLoading")

@Test void promptTemplateFromResource() { String answer = ChatClient.create(model).prompt() .user(u -> u .text(promptTemplate) .param("number", "10") .param("composer", "Michael Giacchino")) .call() .content(); System.out.println(answer); }

Tell me the names of {number} movies whose soundtrack was composed by {composer}

@Value("classpath:movie_prompt.st") private Resource promptTemplate;


