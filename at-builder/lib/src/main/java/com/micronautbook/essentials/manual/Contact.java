package com.micronautbook.essentials.manual;

import java.util.List;

public record Contact(
        String firstName,
        String lastName,
        String jobTitle,
        String company,
        List<String> urls) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String jobTitle;
        private String company;
        private List<String> urls;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder urls(List<String> urls) {
            this.urls = urls;
            return this;
        }

        public Contact build() {
            return new Contact(firstName, lastName, jobTitle, company, urls);
        }
    }
}