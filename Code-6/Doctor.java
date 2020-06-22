class Doctor extends Staff {
    private long doctorId;
    private String specialization;

    public long getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(long _doctorId) {
        doctorId = _doctorId;
    }

    public String getSpecialization() {
        return this.specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
